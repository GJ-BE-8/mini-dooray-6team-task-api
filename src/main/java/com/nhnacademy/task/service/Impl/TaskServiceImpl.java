package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task createTask(long projectId, long milestoneId, String content) {
        Task task = new Task();
        task.setProjectId(projectId);
        task.setMilestoneId(milestoneId);
        task.setContent(content);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Long taskId, String updatedContent, Long milestoneId) {
        Task task = taskRepository.findById(taskId).
                orElseThrow(() -> new RuntimeException("Task가 없다!"));
        task.setContent(updatedContent);
        task.setMilestoneId(milestoneId);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public List<Task> getTasksByProjectId(long projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}
