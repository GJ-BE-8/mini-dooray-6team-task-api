package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Comment saveComment(long taskId, String writerId, String content) {
        Comment comment = new Comment();
        comment.setTask(taskRepository.findById(taskId).get());
        comment.setWriterId(writerId);
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(long commentId, String updateContent) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new RuntimeException("Comment를 찾을 수 없다!"));

        comment.setContent(updateContent);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public Comment getComment(long commentId) {
        return commentRepository.findById(commentId).get();
    }
}
