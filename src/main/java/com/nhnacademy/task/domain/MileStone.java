package com.nhnacademy.task.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    //Project, MileStone 일대다
    @ManyToOne(optional = false)
    private Project project;

}
