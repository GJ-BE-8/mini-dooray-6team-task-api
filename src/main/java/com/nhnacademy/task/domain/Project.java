package com.nhnacademy.task.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private long projectId;


    @Length(max = 50)
    @Column(name = "project_name", nullable = false)
    private String projectName;


    @NotBlank
    @Length(max = 50)
    private String status;


   @Column(name = "admin_id", nullable = false)
    private long adminId;

   //Project, Tag 양방향
   @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();


    //Project, MileStone 다대다 양방향
    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MileStone> mileStones = new ArrayList<>();
}
