package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.dto.MilestoneDTO;
import com.nhnacademy.task.dto.TagDTO;
import com.nhnacademy.task.repository.MileStoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.MileStoneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public MileStone findById(long milestoneId){
        return mileStoneRepository.findById(milestoneId).get();
    }

    @Override
    @Transactional
    public void deleteMileStone(long milestoneId) {
        taskRepository.deleteByMileStone_MilestoneId(milestoneId);
        mileStoneRepository.deleteById(milestoneId);
    }


    @Override
    public List<MilestoneDTO> getAllMilestonebyProject(long projectId){
       List<MileStone> milestoneList= mileStoneRepository.findAllByProject_ProjectId(projectId);
        return milestoneList.stream()
                .map(MilestoneDTO::new)
                .collect(Collectors.toList());
    }
}
