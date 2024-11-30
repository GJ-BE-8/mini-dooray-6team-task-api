package com.nhnacademy.task.Controller;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.request.ProjectRequest;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project")
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        Project addProject = projectService.saveProject(projectRequest.getProjectName(), projectRequest.getStatus(), projectRequest.getAdminId());
        return addProject;
    }
}
