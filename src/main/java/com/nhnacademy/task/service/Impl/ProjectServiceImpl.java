package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.dto.ProjectUpdateDTO;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.request.ProjectRequest;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project saveProject(String projectName, String status, long adminId) {
        Project project = new Project();
        project.setProjectName(projectName);
        project.setStatus(status);
        project.setAdminId(adminId);
        return projectRepository.save(project);
    }

    // todo delete
    @Override
    public void deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
    }


    @Override
    public Project getProject(long ProjectId){
        return projectRepository.findById(ProjectId).get();
    }

    //프로젝트 수정(이름, 상태)
    @Override
    public Project updateProject(ProjectUpdateDTO projectUpdateDTO) {
        Project project = projectRepository.findById(projectUpdateDTO.getProjectId()).get();
        project.setProjectName(projectUpdateDTO.getProjectName());
        project.setStatus(projectUpdateDTO.getStatus());
        return projectRepository.save(project);
    }
}
