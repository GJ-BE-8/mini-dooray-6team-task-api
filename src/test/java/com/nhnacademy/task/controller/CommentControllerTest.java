package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.request.CommentRequest;
import com.nhnacademy.task.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Comment comment;
    private Task task;
    private Project project;

    @BeforeEach
    void setUp() {
        // Project 설정
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setStatus("active");
        project.setAdminId(1L);

        // Task 설정
        task = new Task();
        task.setTaskId(1L);
        task.setContent("Test Task Content");
        task.setProject(project);  // Task에 Project 설정

        // Comment 설정
        comment = new Comment();
        comment.setCommentId(1L);
        comment.setWriterId("user1");
        comment.setContent("This is a test comment.");
        comment.setTask(task);  // Comment에 Task 설정
    }

    @Test
    void testCreateComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest(1L, "user1", "This is a test comment.");

        // 매처를 정확히 사용하여 호출
        Mockito.when(commentService.saveComment(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString())).thenReturn(comment);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1L))
                .andExpect(jsonPath("$.writerId").value("user1"))
                .andExpect(jsonPath("$.content").value("This is a test comment."))
                .andExpect(jsonPath("$.task.taskId").value(1L))
                .andExpect(jsonPath("$.task.project.projectId").value(1L));
    }

    @Test
    void testGetComment() throws Exception {
        Mockito.when(commentService.getComment(eq(1L))).thenReturn(comment);

        mockMvc.perform(get("/comments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1L))
                .andExpect(jsonPath("$.writerId").value("user1"))
                .andExpect(jsonPath("$.content").value("This is a test comment."))
                .andExpect(jsonPath("$.task.taskId").value(1L))
                .andExpect(jsonPath("$.task.project.projectId").value(1L));
    }

    @Test
    void testDeleteComment() throws Exception {
        Mockito.doNothing().when(commentService).deleteComment(eq(1L));

        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isOk());
    }

    // updateComment 테스트 추가
    @Test
    void testUpdateComment() throws Exception {
        String updatedContent = "Updated comment content"; // 업데이트된 내용

        CommentRequest commentRequest = new CommentRequest(1L, "user1", updatedContent);

        // mock 서비스에서 업데이트된 comment 반환
        Comment updatedComment = new Comment();
        updatedComment.setCommentId(1L);
        updatedComment.setWriterId("user1");
        updatedComment.setContent(updatedContent);
        updatedComment.setTask(task);

        Mockito.when(commentService.updateComment(eq(1L), eq(updatedContent))).thenReturn(updatedComment);

        // PUT 요청을 보내고, 반환된 JSON을 검증
        mockMvc.perform(put("/comments/1") // PUT으로 수정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1L))
                .andExpect(jsonPath("$.writerId").value("user1"))
                .andExpect(jsonPath("$.content").value(updatedContent)) // 업데이트된 내용 확인
                .andExpect(jsonPath("$.task.taskId").value(1L))
                .andExpect(jsonPath("$.task.project.projectId").value(1L));
    }
}