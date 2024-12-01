package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.request.TaskRequest;
import com.nhnacademy.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/task")
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        long projectId = taskRequest.getProjectId();
        long mileStoneId = taskRequest.getMileStoneId();
        return taskService.saveTask(projectId, mileStoneId, taskRequest.getContent());
    }

    @PostMapping("/task/update")
    public Task updateTask(@RequestBody TaskRequest taskRequest) {
        long taskId = taskRequest.getTaskId();
        String content = taskRequest.getContent();
        long mileStoneId = taskRequest.getMileStoneId();

        return taskService.updateTask(taskId, content, mileStoneId);
    }

    @DeleteMapping("/tasks/delete/{taskId}")
    public void deleteTask(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
    }


    @GetMapping("/tasks/{projectId}")
    public List<Task> getTasks(@PathVariable("projectId") long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }


}
