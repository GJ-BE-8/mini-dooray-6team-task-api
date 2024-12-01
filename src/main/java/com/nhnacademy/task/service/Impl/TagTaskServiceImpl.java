package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TagTaskRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.request.TagTaskRequest;
import com.nhnacademy.task.service.TagTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagTaskServiceImpl implements TagTaskService {

    private final TagTaskRepository tagTaskRepository;
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public TagTask saveTagTask(TagTaskRequest tagTaskRequest) {
        TagTask tagTask = new TagTask();

        tagTask.setTag(tagRepository.findById(tagTaskRequest.getTagId()).orElse(null));
        tagTask.setTask(taskRepository.findById(tagTaskRequest.getTaskId()).orElse(null));

        return tagTaskRepository.save(tagTask);
    }

    @Override
    @Transactional
    public void deleteTagTask(long tagTaskId) {
        tagTaskRepository.deleteById(tagTaskId);
    }
}
