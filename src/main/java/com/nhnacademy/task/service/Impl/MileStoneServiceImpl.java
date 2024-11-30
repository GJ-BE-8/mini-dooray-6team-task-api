package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.MileStoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileStoneServiceImpl implements MileStoneService {

    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public MileStone addMilestoneToProject(Long projectId, MileStone milestone) {
        //MileStone mileStone = new MileStone(); -- 파라미터를 String milestoneName -> MileStone객체 받는걸로 바꿨음
        milestone.setProject(projectRepository.findById(projectId).get());
//        mileStone.setMilestoneName(milestoneName); milestone 타입을 몰라서 우선 주석
        return mileStoneRepository.save(milestone);
    }

    @Override
    @Transactional
    public void deleteMileStone(long milestoneId) {
        taskRepository.deleteByMileStoneId(milestoneId);
        mileStoneRepository.deleteById(milestoneId);
    }
}
