package com.nhnacademy.task.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectMember {

    private long memberId; // member table에서 PK 의미함
    private long projectId;
}
