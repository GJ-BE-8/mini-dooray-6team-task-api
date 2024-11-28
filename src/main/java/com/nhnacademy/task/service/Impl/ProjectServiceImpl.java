package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
}
