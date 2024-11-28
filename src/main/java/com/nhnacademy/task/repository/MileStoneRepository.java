package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
}
