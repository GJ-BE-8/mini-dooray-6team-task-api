package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Project;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Sql("project.sql")
    @Test
    void findProjectTest(){
        long projectId = 1L;

        Project project = projectRepository.findById(projectId).orElse(null);

        assertThat(project).isNotNull();
        assertThat(project.getProjectId()).isEqualTo(projectId);
        assertThat(project.getProjectName()).isEqualTo("프로젝트1");

    }

}