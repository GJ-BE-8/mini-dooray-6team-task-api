package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.dto.TagDTO;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TagTaskRepository;
import com.nhnacademy.task.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagTaskRepository tagTaskRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Tag addTagToProject(long projectId, String tagName) {
        Tag tag = new Tag();
        tag.setProject(projectRepository.findById(projectId).get());
        tag.setTagName(tagName);
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(long tagId) {
        tagTaskRepository.deleteByTag_TagId(tagId);
        tagRepository.deleteById(tagId);
    }

    @Override
    public Tag getTagById(long tagId) {
        return tagRepository.findById(tagId).get();
    }

    @Override
    public List<Tag> getAllTagByTask(long taskId){
        return tagRepository.findAllByTask(taskId);
    }

    @Override
    public List<TagDTO> getAllTagByProjectId(long projectId) {
        List<Tag> tagList = tagRepository.findAllByProject_ProjectId(projectId);
        return tagList.stream()
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }
  
    @Override
    public Tag updateTag(Long tagId, String tagName) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("태그 없음! "));

        tag.setTagName(tagName);

        return tagRepository.save(tag);
    }
}

