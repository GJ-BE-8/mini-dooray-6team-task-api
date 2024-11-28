package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.MileStoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileStoneServiceImpl implements MileStoneService {

    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public MileStone addMilestoneToProject(Long projectId, String milestoneName) {
        MileStone mileStone = new MileStone();
        mileStone.setProject(projectRepository.findById(projectId).get());
//        mileStone.setMilestoneName(milestoneName); milestone 타입을 몰라서 우선 주석
        return mileStoneRepository.save(mileStone);
    }
}
