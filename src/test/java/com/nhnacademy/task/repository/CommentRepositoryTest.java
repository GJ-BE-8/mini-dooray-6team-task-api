package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Sql("comment.sql")
    @Test
    void findCommentTest(){
        long commentId = 1L;

        Comment comment = commentRepository.findById(commentId).orElse(null);

        assertThat(comment).isNotNull();
        assertThat(comment.getCommentId()).isEqualTo(commentId);
        assertThat(comment.getWriterId()).isEqualTo("user1");
    }

}