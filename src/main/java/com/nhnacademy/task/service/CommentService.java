package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.Comment;

public interface CommentService {
    //새 코멘트 등록
    Comment saveComment(long taskId, String writerId, String content);

    //코멘트 수정
    Comment updateComment(long commentId, String updateContent);

    //코멘트 삭제
    void deleteComment(long commentId);

    //comment get
    Comment getComment(long commentId);
}
