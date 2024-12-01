package com.nhnacademy.task.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProjectUpdateDTO {
    private long projectId;
    private String projectName;
    private String status;
}
