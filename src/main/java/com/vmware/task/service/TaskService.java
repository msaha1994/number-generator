package com.vmware.task.service;

import com.vmware.task.entity.Task;

import java.util.List;

public interface TaskService {
    Task getTaskStatus(String taskUUID);

    String getNumList(String taskUUID);
}
