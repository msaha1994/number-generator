package com.vmware.task.service;

import com.vmware.task.entity.Task;

import java.io.IOException;
import java.util.List;

public interface FileOperations {
    void write(Task t) throws IOException;

    String read(String uuid);
}
