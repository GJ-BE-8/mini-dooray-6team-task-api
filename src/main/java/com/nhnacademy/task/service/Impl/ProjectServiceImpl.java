package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.repository.ProjectRepository;
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

}
