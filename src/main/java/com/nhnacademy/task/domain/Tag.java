package com.nhnacademy.task.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private long tagId;

    @Column(name = "tag_name", nullable = false)
    @Length(max = 50)
    private String tagName;

    @Column(name = "project_id", nullable = false)
    private String projectId;



    //Tag, TagTask 양방향
    @OneToMany(mappedBy = "tag", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TagTask> tagTasks = new ArrayList<>();


    //Project, Tag 양방향
    @ManyToOne(optional = false)
    private Project project;

}
