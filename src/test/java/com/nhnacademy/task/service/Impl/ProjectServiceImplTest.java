package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        // 테스트할 Project 객체 초기화
        project = new Project();
        project.setProjectName("프로젝트1");
        project.setStatus("활성");
        project.setAdminId(100L);
    }

    @Test
    void testSaveProject() {
        // Arrange
        String projectName = "프로젝트1";
        String status = "활성";
        long adminId = 100L;

        // Mockito가 projectRepository.save 호출 시 반환할 값을 설정
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        Project savedProject = projectService.saveProject(projectName, status, adminId);

        // Assert
        assertNotNull(savedProject);
        assertEquals(projectName, savedProject.getProjectName());
        assertEquals(status, savedProject.getStatus());
        assertEquals(adminId, savedProject.getAdminId());

        // Mockito 검증
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}
