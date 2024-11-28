package com.nhnacademy.task.domain;

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

    @Column(name = "project_id", nullable = false)
    private long projectId;

    @Column(name = "milestone_id", nullable = false)
    private long milestoneId;

    @Column(length = 300)
    private String content;


    @OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TagTask> tagTasks = new ArrayList<>();


    //MileStone, Task 양방향
    @ManyToOne(optional = false)
    private MileStone mileStone;
}