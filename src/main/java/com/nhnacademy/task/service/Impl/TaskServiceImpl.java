package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;

    @Override
    @Transactional
    public Task saveTask(long projectId, long milestoneId, String content) {
        Task task = new Task();
        task.setProject(projectRepository.findById(projectId).get());
        task.setMileStone(mileStoneRepository.findById(milestoneId).get());
        task.setContent(content);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Long taskId, String updatedContent, Long milestoneId) {
        Task task = taskRepository.findById(taskId).
                orElseThrow(() -> new RuntimeException("Task가 없다!"));
        task.setContent(updatedContent);
        task.setMileStone(mileStoneRepository.findById(milestoneId).get());
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(long taskId) {
        // todo task 참조하는 comment 삭제해야함 and tag_task 삭제해야함 by taskId
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public List<Task> getTasksByProjectId(long projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }



}
