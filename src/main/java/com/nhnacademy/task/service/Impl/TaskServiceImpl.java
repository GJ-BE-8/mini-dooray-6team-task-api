package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.dto.TaskDTO;
import com.nhnacademy.task.repository.*;
import com.nhnacademy.task.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;
    private final CommentRepository commentRepository;
    private final TagTaskRepository tagTaskRepository;

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
        commentRepository.deleteByTask_TaskId(taskId);
        tagTaskRepository.deleteByTask_TaskId(taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public List<TaskDTO> getTasksByProjectId(long projectId) {
        List<Task> taskList = taskRepository.findAllByProject_ProjectId(projectId);
        return taskList.stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }



}
