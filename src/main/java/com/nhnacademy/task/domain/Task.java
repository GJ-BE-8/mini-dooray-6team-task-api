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

//    @Column(name = "project_id", nullable = false)
//    private long projectId;

//    @Column(name = "milestone_id", nullable = false)
//    private long milestoneId;

    @Column(length = 300)
    private String content;


    @OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TagTask> tagTasks = new ArrayList<>();


    //MileStone, Task 양방향
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonBackReference
    private MileStone mileStone;

    //Project, Task 양방향
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonBackReference
    private Project project;

    //Project, Comment 양방향
    @OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

}
