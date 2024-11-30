package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.request.MileStoneRequest;
import com.nhnacademy.task.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MileStoneController {
    private final MileStoneService mileStoneService;


    @PostMapping("/milestone")
    public MileStone createMileStone(@RequestBody MileStoneRequest mileStoneRequest) {
        MileStone mileStone = mileStoneService.addMilestoneToProject(mileStoneRequest.getProjectId(), mileStoneRequest.getMileStoneName());
        return mileStone;
    }


    @PostMapping("/milestone/delete/{milestoneId}")
    public void deleteMileStone(@PathVariable Long milestoneId) {
        mileStoneService.deleteMileStone(milestoneId);
    }
}
