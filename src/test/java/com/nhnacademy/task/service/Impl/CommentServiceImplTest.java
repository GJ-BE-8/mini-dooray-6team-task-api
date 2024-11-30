package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.Impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private Task task;

    @BeforeEach
    void setUp() {
        // Task 객체 및 Comment 객체 초기화
        task = new Task();
        task.setTaskId(1L);
        task.setContent("Test Task");

        comment = new Comment();
        comment.setCommentId(1L);
        comment.setWriterId("writer123");
        comment.setContent("Test Comment");
        comment.setTask(task);
    }

    @Test
    void testSaveComment() {
        // Arrange
        long taskId = 1L;
        String writerId = "writer123";
        String content = "Test Comment";

        // Mockito 설정: taskRepository에서 해당 task를 반환하도록 설정
        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Act
        Comment savedComment = commentService.saveComment(taskId, writerId, content);

        // Assert
        assertNotNull(savedComment);
        assertEquals(writerId, savedComment.getWriterId());
        assertEquals(content, savedComment.getContent());
        assertEquals(taskId, savedComment.getTask().getTaskId());

        // Mockito 검증: findById 및 save 호출 확인
        verify(taskRepository, times(1)).findById(taskId);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testUpdateComment() {
        // Arrange
        long commentId = 1L;
        String updatedContent = "Updated Comment";

        // Mockito 설정: commentRepository에서 해당 comment를 반환하도록 설정
        when(commentRepository.findById(commentId)).thenReturn(java.util.Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Act
        Comment updatedComment = commentService.updateComment(commentId, updatedContent);

        // Assert
        assertNotNull(updatedComment);
        assertEquals(updatedContent, updatedComment.getContent());

        // Mockito 검증: findById 및 save 호출 확인
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        // Arrange
        long commentId = 1L;

        // Act
        commentService.deleteComment(commentId);

        // Assert
        // Mockito 검증: deleteById 호출 확인
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
