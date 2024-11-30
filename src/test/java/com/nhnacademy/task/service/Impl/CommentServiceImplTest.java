package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentServiceImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;


    private final CommentService commentService;


    public CommentServiceImplTest() {
        this.commentService =  new CommentServiceImpl(commentRepository, taskRepository);
    }

    @Test
    void saveComment() {
        long taskId = 1L;
        String writerId = "testId";
        String content = "testContent";

        Comment savedComment = commentService.saveComment(taskId, writerId, content);

        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getContent()).isEqualTo(content);
        assertThat(savedComment.getWriterId()).isEqualTo(writerId);
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }
}