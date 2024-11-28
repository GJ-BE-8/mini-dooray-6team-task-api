package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Tag addTagToProject(long projectId, String tagName) {
        Tag tag = new Tag();
        tag.setProject(projectRepository.findById(projectId).get());
        tag.setTagName(tagName);
        return tagRepository.save(tag);
    }
}
