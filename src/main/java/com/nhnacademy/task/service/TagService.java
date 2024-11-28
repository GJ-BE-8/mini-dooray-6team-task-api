package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.Tag;

public interface TagService {
    Tag addTagToProject(long projectId, String tagName);

}
