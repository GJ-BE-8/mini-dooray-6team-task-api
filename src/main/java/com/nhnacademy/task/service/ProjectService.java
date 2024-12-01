package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.Project;

public interface ProjectService {

    //새 프로젝트 생성 - post로 create command가 들어올 건데 게이트 웨이랑 맞춰야함
    Project saveProject(String projectName, String status, long adminId);

    //프로젝트에 마일스톤 추가
    //프로젝트에 태그 추가
    //태그 삭제(task에서도 삭제됨)
    //마일스톤 삭제(task에서도 삭제됨)

    void deleteProject(long projectId);
}
