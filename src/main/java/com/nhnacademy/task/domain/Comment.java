package com.nhnacademy.task.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "task_id")
    private long taskId;

    @Column(name = "wirter_id", nullable = false, length = 50)
    private String writerId;

    @Column(length = 255, nullable = false)
    private String content;

}
