package com.vmware.task;

import com.vmware.task.entity.Status;
import com.vmware.task.entity.Task;
import com.vmware.task.repository.TaskRepository;
import com.vmware.task.service.TaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service("taskGenerator")
public class TaskGenerator implements Generator{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    public String generate(int goal, int step) throws Exception {
        Task t = populateTask(goal,step);
        taskExecutor.execute(t);
        return t.getTaskId();
    }
    private Task populateTask(int goal, int step){
        Task t = new Task();
        t.setTaskId(UUID.randomUUID().toString());
        t.setStatus(Status.IN_PROGRESS);
        t.setGoal(goal);
        t.setStep(step);
        taskRepository.save(t);
        return t;
    }
}
