package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.dto.TagDTO;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public interface TagService {
    //프로젝트에 태그 추가
    Tag addTagToProject(long projectId, String tagName);

    //태그 삭제 (task에서도 삭제됨)
    void deleteTag(long tagId);

    //get
    Tag getTagById(long tagId);

    //태스크에 있는 모든 태그 가져오기
    public List<Tag> getAllTagByTask(long taskId);


    //특정 프로젝트의 모든 태그 가져오기
    public List<TagDTO> getAllTagByProjectId(long projectId);

    Tag updateTag(Long tagId, String tagName);

}
