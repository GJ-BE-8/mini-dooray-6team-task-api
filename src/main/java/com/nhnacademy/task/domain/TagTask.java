package com.nhnacademy.task.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class TagTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_task_id")
    private long tagTaskId;

//    @Column(name = "tag_id", nullable = false)
//    private long tagId;
    @ManyToOne(optional = false)
    private Tag tag;

//    @Column(name = "task_id", nullable = false)
//    private long taskId;

    @ManyToOne(optional = false)
    private Task task;

}
