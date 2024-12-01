package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.request.ProjectRequest;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project")
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        Project addProject = projectService.saveProject(projectRequest.getProjectName(), projectRequest.getStatus(), projectRequest.getAdminId());
        return addProject;
    }


    @GetMapping("/project/{projectId}")
    public Project getProject(@PathVariable Long projectId) {
        return projectService.getProject(projectId);
    }

    @DeleteMapping("/project/delete/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
