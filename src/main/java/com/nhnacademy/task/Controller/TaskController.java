package com.nhnacademy.task.Controller;

import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        long projectId = task.getProject().getProjectId();
        long mileStoneId = task.getMileStone().getMilestoneId();
        return taskService.saveTask(projectId, mileStoneId, task.getContent()   );
    }

}
