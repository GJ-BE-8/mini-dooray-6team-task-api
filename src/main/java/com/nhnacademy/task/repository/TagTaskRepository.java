package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.TagTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagTaskRepository extends JpaRepository<TagTask, Long> {
    void deleteByTagId(Long tagId);

}
