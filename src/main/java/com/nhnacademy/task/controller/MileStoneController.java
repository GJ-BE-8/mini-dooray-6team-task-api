package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.request.MileStoneRequest;
import com.nhnacademy.task.service.MileStoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MileStoneController {
    private final MileStoneService mileStoneService;


    @PostMapping("/milestone")
    @Transactional
    public MileStone createMileStone(@RequestBody MileStoneRequest mileStoneRequest) {
        MileStone mileStone = mileStoneService.addMilestoneToProject(mileStoneRequest.getProjectId(), mileStoneRequest.getMileStoneName());
        return mileStone;
    }

    @GetMapping("/milestone/{milestoneId}")
    @Transactional
    public MileStone getMileStone(@PathVariable Long milestoneId) {
        MileStone mileStone = mileStoneService.findById(milestoneId);
        return mileStone;
    }


    @PostMapping("/milestone/delete/{milestoneId}")
    public void deleteMileStone(@PathVariable Long milestoneId) {
        mileStoneService.deleteMileStone(milestoneId);
    }
}
