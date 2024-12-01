package com.nhnacademy.task.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private long milestoneId;

    @NotBlank
    private String milestoneName;

    //Project, MileStone 다대다 양방향
    @ManyToOne(optional = false)
    @JsonIgnore
    private Project project;

    //MileStone, Task 양방향
    @OneToMany(mappedBy = "mileStone", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Task> tasks;
}
