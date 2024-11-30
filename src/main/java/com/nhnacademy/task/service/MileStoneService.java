package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.MileStone;

public interface MileStoneService {
    MileStone addMilestoneToProject(Long projectId, String milestoneName);
    //mileStone 반환 타입 몰라서 일단 String으로 진행

    void deleteMileStone(long milestoneId);
}
