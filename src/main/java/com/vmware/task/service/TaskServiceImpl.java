package com.vmware.task.service;

import com.vmware.task.entity.Task;
import com.vmware.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Value("${home.folder}")
    private String folderLocation;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FileOperations fileOperations;

    @Override
    public Task getTaskStatus(String taskUUID) {
        Task t = taskRepository.findByTaskId(taskUUID);
        return t;
    }

    @Override
    public String getNumList(String taskUUID) {
        return fileOperations.read(taskUUID);
    }
}
