package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.dto.MilestoneDTO;

import java.util.List;

public interface MileStoneService {
    MileStone addMilestoneToProject(Long projectId, String milestoneName);
    //mileStone 반환 타입 몰라서 일단 String으로 진행

    void deleteMileStone(long milestoneId);
    MileStone findById(long milestoneId);
    List<MilestoneDTO> getAllMilestonebyProject(long projectId);
}
