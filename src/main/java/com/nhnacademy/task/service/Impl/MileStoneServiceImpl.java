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
    public MileStone addMilestoneToProject(Long projectId, String milestoneName) {
        MileStone mileStone = new MileStone();
        mileStone.setProject(projectRepository.findById(projectId).get());
        mileStone.setMilestoneName(milestoneName);
        return mileStoneRepository.save(mileStone);
    }

    @Override
    @Transactional
    public void deleteMileStone(long milestoneId) {
        taskRepository.deleteByMileStoneId(milestoneId);
        mileStoneRepository.deleteById(milestoneId);
    }
}
