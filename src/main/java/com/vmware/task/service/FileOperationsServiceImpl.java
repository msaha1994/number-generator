package com.vmware.task.service;

import com.vmware.task.entity.Status;
import com.vmware.task.entity.Task;
import com.vmware.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("fileOperator")
public class FileOperationsServiceImpl implements FileOperations {

    @Value("${home.folder}")
    private String folderLocation;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void write(Task t) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(t.getFilePath()));
        int goal = t.getGoal();
        int step = t.getStep();
        String buffer = "";
        while (goal != 0) {
            int temp = step;
            while (temp != 0 && goal != 0) {
                buffer = buffer + " " + goal;
                temp--;
                goal--;
            }
            writer.write(buffer);
            buffer = "";
        }

        t.setStatus(Status.COMPLETED);
        taskRepository.save(t);

        writer.close();
    }

    @Override
    public String read(String uuid) {
        String fileName = folderLocation + uuid + ".txt";
        List<String> list;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            //Log here
            e.printStackTrace();
            throw new RuntimeException("Problem with file");
        }
        return list.toString();
    }
}
