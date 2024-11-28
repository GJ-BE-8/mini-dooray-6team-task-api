package com.nhnacademy.task.Controller;

import com.nhnacademy.task.domain.Comment;
import com.nhnacademy.task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment createComment(@RequestParam long commentId, @RequestParam String commentText) {

    }
}
