package com.nhnacademy.task.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id", nullable = false)
    private long milestoneId;


    @Column(name = "project_id", nullable = false)
    private long productId;

    //마일 스톤 일단 뺏음

    //Project, MileStone 다대다 양방향
    @ManyToOne(optional = false)
    private Project project;

    //MileStone, Task 양방향
    @OneToMany(mappedBy = "mileStone", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}
