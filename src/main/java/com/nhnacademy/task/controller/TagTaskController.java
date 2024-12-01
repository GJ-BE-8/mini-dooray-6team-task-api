package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.request.TagTaskRequest;
import com.nhnacademy.task.service.TagTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TagTaskController {

    private final TagTaskService tagTaskService;


    @PostMapping("/tagtask")
    public TagTask createTagTask(@RequestBody TagTaskRequest tagTaskRequest) {
        return tagTaskService.saveTagTask(tagTaskRequest);
    }


    @DeleteMapping("/tagtask/delete/{tagTaskId}")
    public void deleteTagTask(@PathVariable Long tagTaskId) {
        tagTaskService.deleteTagTask(tagTaskId);
    }
}
