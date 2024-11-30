package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MilestoneServiceImplTest {
    @Mock
    private MileStoneRepository mileStoneRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;


    @InjectMocks
    private MileStoneServiceImpl mileStoneService;

    private Project project;
    private MileStone milestone;

    @BeforeEach
    void setUp() {
        // Mock 객체 설정
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setStatus("Active");
        project.setAdminId(100L);

        milestone = new MileStone();
        milestone.setMilestoneName("Test Milestone");
        milestone.setProject(project);
    }

    @Test
    void testAddMilestoneToProject() {
        // Arrange
        Long projectId = 1L;
        String milestoneName = "New Milestone";

        // Mockito가 projectRepository.findById를 호출할 때 반환할 값을 지정
        when(projectRepository.findById(projectId)).thenReturn(java.util.Optional.of(project));
        when(mileStoneRepository.save(any(MileStone.class))).thenReturn(milestone);

        // Act
        MileStone savedMilestone = mileStoneService.addMilestoneToProject(projectId, milestoneName);

        // Assert
        assertNotNull(savedMilestone);
        assertEquals("Test Milestone", savedMilestone.getMilestoneName());
        assertEquals(1L, savedMilestone.getProject().getProjectId());

        // Mockito 검증
        verify(projectRepository, times(1)).findById(projectId);
        verify(mileStoneRepository, times(1)).save(any(MileStone.class));
    }

    @Test
    void testDeleteMileStone() {
        // Arrange
        long milestoneId = 1L;

        // Act
        mileStoneService.deleteMileStone(milestoneId);

        // Assert
        verify(taskRepository, times(1)).deleteByMileStone_MilestoneId(milestoneId);
        verify(mileStoneRepository, times(1)).deleteById(milestoneId);
    }
}



