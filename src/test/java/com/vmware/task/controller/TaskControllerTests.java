package com.vmware.task.controller;

import com.vmware.NumberGeneratorApplicationTests;
import com.vmware.task.entity.Status;
import com.vmware.task.entity.Task;
import com.vmware.task.response.GenericResponse;
import com.vmware.task.service.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTests extends NumberGeneratorApplicationTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void shouldReturn404WhenTaskIdNotPresentInDB() throws Exception {
        when(taskService.getTaskStatus("abc")).thenReturn(null);
        mvc.perform(get("/api/tasks/abc/status")
        ).andExpect(status().isNotFound())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype()
                )));

    }
    @Test
    public void shouldReturnValidStatusOfTask() throws Exception {
        Task t = new Task();
        t.setStatus(Status.COMPLETED);
        when(taskService.getTaskStatus("abc"))
                .thenReturn(t);
        mvc.perform(get("/api/tasks/abc/status")
        ).andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype()
                )))
                .andExpect(jsonPath("$.result", is("COMPLETED")));

    }
}
