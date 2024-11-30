package com.nhnacademy.task.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id", nullable = false)
    private long milestoneId;


//    @Column(name = "project_id", nullable = false)
//    private long productId;

    @Column(name = "milestone_name", nullable = false)
    private String milestoneName;

    //Project, MileStone 다대다 양방향
    @ManyToOne(optional = false)
    private Project project;

    //MileStone, Task 양방향
    @OneToMany(mappedBy = "mileStone", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}
