package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT t.tag_name FROM tag t INNER JOIN tag_task tt ON t.tag_id = tt.tag_id WHERE tt.task_id = :taskId", nativeQuery = true)
    List<Tag> findAllByTask(long taskId);


    List<Tag> findAllByProject_ProjectId(long projectId);
}
