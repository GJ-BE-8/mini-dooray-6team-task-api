package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.repository.TagTaskRepository;
import com.nhnacademy.task.service.TagTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagTaskServiceImpl implements TagTaskService {

    private final TagTaskRepository tagTaskRepository;

    @Override
    @Transactional
    public TagTask saveTagTask(TagTask tagTask) {
        return tagTaskRepository.save(tagTask);
    }

    @Override
    @Transactional
    public void deleteTagTask(TagTask tagTask) {
        tagTaskRepository.deleteById(tagTask.getTagTaskId());
    }
}
