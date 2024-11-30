package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.request.CommentRequest;
import com.nhnacademy.task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //Comment 생성
    @PostMapping("/comments")
    public Comment createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.saveComment(commentRequest.getTaskId(), commentRequest.getWriterId(), commentRequest.getContent());
    }

    //Comment 수정
    @PostMapping("/comments/{commentId}")
    public Comment updateComment(@PathVariable long commentId, @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(commentId, commentRequest.getContent());
    }

    //Comment 삭제
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId")long commentId) {
        commentService.deleteComment(commentId);
    }
}
