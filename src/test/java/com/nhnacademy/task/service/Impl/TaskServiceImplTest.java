package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private MileStoneRepository mileStoneRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Project project;
    private MileStone mileStone;
    private Task task;

    @BeforeEach
    void setUp() {
        // Mock 객체 설정
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setStatus("Active");
        project.setAdminId(100L);

        mileStone = new MileStone();
        mileStone.setMilestoneId(1L);
        mileStone.setMilestoneName("Test Milestone");

        task = new Task();
        task.setTaskId(1L);
        task.setProject(project);
        task.setMileStone(mileStone);
        task.setContent("Initial Task Content");
    }

    @Test
    void testSaveTask() {
        Long projectId = 1L;
        Long milestoneId = 1L;
        String content = "New Task Content";

        Task newTask = new Task();
        newTask.setTaskId(1L);
        newTask.setProject(project);
        newTask.setMileStone(mileStone);
        newTask.setContent(content);

        // Mockito가 projectRepository.findById와 mileStoneRepository.findById를 호출할 때 반환할 값을 지정
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(mileStoneRepository.findById(milestoneId)).thenReturn(Optional.of(mileStone));
        when(taskRepository.save(any(Task.class))).thenReturn(newTask);

        // Act
        Task savedTask = taskService.saveTask(projectId, milestoneId, content);

        // Assert
        assertNotNull(savedTask);
        assertEquals(content, savedTask.getContent());
        assertEquals(projectId, savedTask.getProject().getProjectId());
        assertEquals(milestoneId, savedTask.getMileStone().getMilestoneId());

        // Mockito 검증
        verify(projectRepository, times(1)).findById(projectId);
        verify(mileStoneRepository, times(1)).findById(milestoneId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdateTask() {
        // Arrange
        Long taskId = 1L;
        String updatedContent = "Updated Task Content";
        Long milestoneId = 1L;

        // Mockito가 taskRepository.findById와 mileStoneRepository.findById를 호출할 때 반환할 값을 지정
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(mileStoneRepository.findById(milestoneId)).thenReturn(Optional.of(mileStone));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task updatedTask = taskService.updateTask(taskId, updatedContent, milestoneId);

        // Assert
        assertNotNull(updatedTask);
        assertEquals(updatedContent, updatedTask.getContent());
        assertEquals(milestoneId, updatedTask.getMileStone().getMilestoneId());

        // Mockito 검증
        verify(taskRepository, times(1)).findById(taskId);
        verify(mileStoneRepository, times(1)).findById(milestoneId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testDeleteTask() {
        // Arrange
        Long taskId = 1L;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testGetTasksByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findByProject_ProjectId(projectId)).thenReturn(tasks);

        // Act
        List<Task> taskList = taskService.getTasksByProjectId(projectId);

        // Assert
        assertNotNull(taskList);
        assertEquals(1, taskList.size());
        assertEquals(projectId, taskList.get(0).getProject().getProjectId());

        // Mockito 검증
        verify(taskRepository, times(1)).findByProject_ProjectId(projectId);
    }
}
