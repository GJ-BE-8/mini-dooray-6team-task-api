package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.ProjectMember;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-members")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberRepository repository;

    // add
    @PostMapping
    public List<Long> addMemberProject(@RequestBody ProjectMember request){
        return repository.addProjectMember(request.getMemberId(),request.getProjectId());
    }

    //delete
    @DeleteMapping
    public ResponseEntity deleteMemberProject(@RequestBody ProjectMember request){
        repository.deleteProjectMember(request.getMemberId(),request.getProjectId());
        return ResponseEntity.ok().build();
    }

    //get
    @GetMapping("/{projectId}")
    public List<Long> getMemberProject(@PathVariable long projectId){
        return repository.getProjectMember(projectId);
    }
}
