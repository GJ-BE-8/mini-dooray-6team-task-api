package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
    List<MileStone> findAllByProject_ProjectId(Long projectId);

}
