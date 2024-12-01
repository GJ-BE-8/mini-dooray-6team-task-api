package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //특정 프로젝트에 해당하는 task 조회
    List<Task> findAllByProject_ProjectId(long projectId);

    //task에서 마일스톤 아이디 삭제
    void deleteByMileStone_MilestoneId(Long milestoneId);

}
