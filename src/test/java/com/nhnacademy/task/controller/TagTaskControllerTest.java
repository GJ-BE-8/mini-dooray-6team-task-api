package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.request.TagTaskRequest;
import com.nhnacademy.task.service.TagTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagTaskController.class)
class TagTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagTaskService tagTaskService;

    @Autowired
    private ObjectMapper objectMapper;

    private TagTask tagTask;
    private Tag tag;
    private Task task;

    @BeforeEach
    void setUp() {
        // Test용 Tag와 Task 객체 생성
        tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("Test Tag");

        task = new Task();
        task.setTaskId(1L);
        task.setContent("Test Task");

        // Test용 TagTask 객체 생성
        tagTask = new TagTask();
        tagTask.setTagTaskId(1L);
        tagTask.setTag(tag);
        tagTask.setTask(task);
    }

    @Test
    void testCreateTagTask() throws Exception {
        TagTaskRequest tagTaskRequest = new TagTaskRequest(1L, 1L);  // taskId와 tagId를 요청으로 전달

        // TagTaskService에서 saveTagTask 메소드를 호출할 때 mock 객체를 반환하도록 설정
        Mockito.when(tagTaskService.saveTagTask(any(TagTaskRequest.class))).thenReturn(tagTask);

        // POST 요청을 보내고, 반환되는 JSON을 검증
        mockMvc.perform(post("/tagtask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagTaskRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tagTaskId").value(1L))
                .andExpect(jsonPath("$.tag.tagId").value(1L))
                .andExpect(jsonPath("$.task.taskId").value(1L));
    }

    @Test
    void testDeleteTagTask() throws Exception {
        // deleteTagTask 메소드가 정상적으로 호출될 때 아무 동작도 하지 않도록 mock 설정
        Mockito.doNothing().when(tagTaskService).deleteTagTask(eq(1L));

        // DELETE 요청을 보내고 상태 코드 200 OK를 검증
        mockMvc.perform(delete("/tagtask/delete/1"))
                .andExpect(status().isOk());
    }
}