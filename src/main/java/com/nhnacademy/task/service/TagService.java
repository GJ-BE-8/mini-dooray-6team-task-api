package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.Tag;

public interface TagService {
    //프로젝트에 태그 추가
    Tag addTagToProject(long projectId, String tagName);

    //태그 삭제 (task에서도 삭제됨)
    void deleteTag(long tagId);

    //get
    Tag getTagById(long tagId);
}
