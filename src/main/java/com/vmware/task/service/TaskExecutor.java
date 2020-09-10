package com.vmware.task.service;

import com.vmware.task.entity.Task;

import java.io.IOException;

public interface TaskExecutor {
    void execute(Task task) throws Exception;
}
