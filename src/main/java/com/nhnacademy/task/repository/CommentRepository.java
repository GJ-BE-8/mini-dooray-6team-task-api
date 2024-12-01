package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByTask_TaskId(Long taskId);
}
