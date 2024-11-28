package com.nhnacademy.task.domain;


import jakarta.persistence.*;
import lombok.*;

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


    @Column(name = "project_id", nullable = false)
    private long productId;

    //마일 스톤 일단 뺏음

}
