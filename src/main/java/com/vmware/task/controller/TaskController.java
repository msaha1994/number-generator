package com.vmware.task.controller;

import com.vmware.task.entity.Task;
import com.vmware.task.request.model.TaskBody;
import com.vmware.task.Generator;
import com.vmware.task.response.GenericResponse;
import com.vmware.task.response.TaskResponse;
import com.vmware.task.service.TaskService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    @Qualifier("taskGenerator")
    private Generator taskGenerator;

    @Autowired
    private TaskService taskServiceImpl;

    @PostMapping("/generate")
    public ResponseEntity<TaskResponse> generateTask(@RequestBody TaskBody tBody) {
        TaskResponse t = new TaskResponse();
        try {
            String taskUid = taskGenerator.generate(tBody.getGoal(), tBody.getStep());
            t.setTaskUid(taskUid);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(t);
        } catch (Exception e) {
            //LOG here
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(t);
        }

    }

    @GetMapping("/tasks/{UUID_task}/status")
    public ResponseEntity<GenericResponse> getStatus(@PathVariable("UUID_task") String uuidTask) {
        GenericResponse g = new GenericResponse();
        try {
            Task t = taskServiceImpl.getTaskStatus(uuidTask);
            if (t != null) {
                g.setResult(t.getStatus().name());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(g);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(g);
            }
        } catch (Exception e) {
            //Log here
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(g);
        }
    }

    @GetMapping("tasks/{UUID_task}")
    public List<String> getContent(@PathVariable("UUID_task") String uuidTask, @RequestParam String action) {
        return taskServiceImpl.getNumList(uuidTask);
    }
}
