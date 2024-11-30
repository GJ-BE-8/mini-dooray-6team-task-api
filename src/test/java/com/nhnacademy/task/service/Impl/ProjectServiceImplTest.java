package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Sql("comment.sql")
    @Test
    void saveProject() {
        String projectName = "프로젝트1";
        String status = "진행중";
        long adminId = 1L;

        Project savedProject = projectService.saveProject(projectName, status, adminId);

        Project foundProject = projectRepository.findById(savedProject.getProjectId()).orElse(null);
        assertThat(foundProject).isNotNull();
        assertThat(foundProject.getProjectName()).isEqualTo(projectName);
        assertThat(foundProject.getStatus()).isEqualTo(status);
        assertThat(foundProject.getAdminId()).isEqualTo(adminId);
    }
}