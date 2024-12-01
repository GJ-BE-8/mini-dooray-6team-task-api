package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.request.MileStoneRequest;
import com.nhnacademy.task.service.MileStoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MileStoneController {
    private final MileStoneService mileStoneService;


    @PostMapping("/milestone")
    public MileStone createMileStone(@RequestBody MileStoneRequest mileStoneRequest) {
        MileStone mileStone = mileStoneService.addMilestoneToProject(mileStoneRequest.getProjectId(), mileStoneRequest.getMileStoneName());
        return mileStone;
    }

    @GetMapping("/milestones/{milestoneId}")
    public MileStone getMileStone(@PathVariable("milestoneId") Long milestoneId) {
        return mileStoneService.findById(milestoneId);
    }


    @DeleteMapping("/milestones/delete/{milestoneId}")
    public void deleteMileStone(@PathVariable Long milestoneId) {
        mileStoneService.deleteMileStone(milestoneId);
    }

    @GetMapping("/project/{projectId}/milestone")
    public List<MileStone> getMilestones(@PathVariable Long projectId) {
        return mileStoneService.getAllMilestonebyProject(projectId);
    }
}
