package com.vmware.task.service;

import com.vmware.task.entity.Status;
import com.vmware.task.entity.Task;
import com.vmware.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("taskExecutor")
public class TaskExecutorServiceImpl implements TaskExecutor {
    @Value("${folder.path}")
    private String folderLocation;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("fileOperator")
    private FileOperations fileOperator;

    @Override
    @Async("singleThreadExecutor")
    public void execute(Task t) throws Exception {
        t.setFilePath(folderLocation + t.getTaskId() + ".txt");
        try {
            fileOperator.write(t);
        } catch (Exception e) {
            //Log here
            t.setStatus(Status.ERROR);
            taskRepository.save(t);
            throw new Exception("Problem in writing into the file with the given filePath");
        }
    }
}
