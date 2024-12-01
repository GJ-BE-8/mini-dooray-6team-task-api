package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.TagTask;

public interface TagTaskService {

    TagTask saveTagTask(TagTask tagTask);
    void deleteTagTask(TagTask tagTask);
}
