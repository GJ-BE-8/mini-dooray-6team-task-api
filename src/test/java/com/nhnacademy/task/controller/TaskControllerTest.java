package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.request.TaskRequest;
import com.nhnacademy.task.service.Impl.TaskServiceImpl;
import com.nhnacademy.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServiceImpl taskService;  // @MockBean을 사용하여 TaskService 주입

    @MockBean
    private Project project;  // @MockBean을 사용하여 Project 주입

    @BeforeEach
    void setUp() {
        // mockMvc 인스턴스를 설정합니다.
        mockMvc = MockMvcBuilders.standaloneSetup(new TaskController(taskService)).build();
    }

    @Test
    void testCreateTask() throws Exception {
        // TaskRequest 객체 생성
        TaskRequest taskRequest = new TaskRequest(0L, 1L, "Test Task", 1L);
        Task task = new Task(1L, "Test Task", null, project);  // Task 객체 생성

        // Mocking 서비스 메서드
        when(taskService.saveTask(anyLong(), anyLong(), any())).thenReturn(task);

        // POST 요청을 MockMvc로 실행
        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)  // Content-Type 설정
                        .content(new ObjectMapper().writeValueAsString(taskRequest)))  // 본문 내용 설정
                .andExpect(status().isOk())  // 상태 코드 OK 확인
                .andExpect(jsonPath("$.taskId").value(1L))  // taskId 확인
                .andExpect(jsonPath("$.content").value("Test Task"));  // content 확인

    }


    @Test
    void testUpdateTask() throws Exception {
        // 업데이트할 TaskRequest 객체 생성
        TaskRequest taskRequest = new TaskRequest(1L, 1L, "Updated Task", 1L);
        Task task = new Task(1L, "Updated Task", null, project);  // Task 객체 생성

        // Mocking 서비스 메서드
        when(taskService.updateTask(anyLong(), any(), anyLong())).thenReturn(task);

        // POST 요청을 MockMvc로 실행
        mockMvc.perform(post("/task/update")
                        .contentType(MediaType.APPLICATION_JSON)  // Content-Type 설정
                        .content(new ObjectMapper().writeValueAsString(taskRequest)))  // 본문 내용 설정
                .andExpect(status().isOk())  // 상태 코드 OK 확인
                .andExpect(jsonPath("$.taskId").value(1L))  // taskId 확인
                .andExpect(jsonPath("$.content").value("Updated Task"))  // content 확인
                .andExpect(jsonPath("$.taskId").value(1L));

    }

    @Test
    void testDeleteTask() throws Exception {
        // 삭제할 때 아무것도 반환하지 않도록 설정
        doNothing().when(taskService).deleteTask(anyLong());

        // DELETE 요청을 MockMvc로 실행
        mockMvc.perform(delete("/tasks/delete/1"))
                .andExpect(status().isOk());  // 상태 코드 OK 확인
    }

    @Test
    void testGetTasks() throws Exception {
        // 여러 개의 Task 객체를 리스트로 생성
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", null, project),
                new Task(2L, "Task 2", null, project)
        );

        // Mocking 서비스 메서드
        when(taskService.getTasksByProjectId(anyLong())).thenReturn(tasks);

        // GET 요청을 MockMvc로 실행
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())  // 상태 코드 OK 확인
                .andExpect(jsonPath("$[0].taskId").value(1L))  // 첫 번째 Task의 taskId 확인
                .andExpect(jsonPath("$[1].taskId").value(2L))  // 두 번째 Task의 taskId 확인
                .andExpect(jsonPath("$[0].content").value("Task 1"))  // 첫 번째 Task의 content 확인
                .andExpect(jsonPath("$[1].content").value("Task 2"));  // 두 번째 Task의 content 확인
    }
}
