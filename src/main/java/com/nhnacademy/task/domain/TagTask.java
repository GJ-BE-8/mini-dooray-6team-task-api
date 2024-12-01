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

    @ManyToOne(optional = false)
    private Tag tag;

    @ManyToOne(optional = false)
    private Task task;

}
