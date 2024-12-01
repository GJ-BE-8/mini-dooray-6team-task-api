package com.nhnacademy.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

}
