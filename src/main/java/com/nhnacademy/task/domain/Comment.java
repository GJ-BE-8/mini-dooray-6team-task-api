package com.nhnacademy.task.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;


    @Column(name = "writer_id", nullable = false, length = 50)
    private String writerId;

    @Column(length = 255, nullable = false)
    private String content;

    //Project, Comment
    @ManyToOne(optional = false)
    private Task task;

}
