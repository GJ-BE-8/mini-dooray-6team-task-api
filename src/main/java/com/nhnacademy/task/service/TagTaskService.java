package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.request.TagTaskRequest;

public interface TagTaskService {

    TagTask saveTagTask(TagTaskRequest tagTaskRequest);
    void deleteTagTask(long tagTaskId);
}
