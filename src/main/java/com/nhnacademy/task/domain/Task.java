package com.nhnacademy.task.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;

    @Column(length = 300)
    private String content;

    //MileStone, Task
    @ManyToOne(optional = false)
    private MileStone mileStone;

    //Project, Task
    @ManyToOne(optional = false)
    private Project project;

}
