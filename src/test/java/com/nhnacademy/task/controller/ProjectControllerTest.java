package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.request.ProjectRequest;
import com.nhnacademy.task.service.Impl.ProjectServiceImpl;
import com.nhnacademy.task.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServiceImpl projectService;

    @Autowired
    private ObjectMapper objectMapper;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setStatus("active");
        project.setAdminId(1L);
    }

    @Test
    void testCreateProject() throws Exception {
        ProjectRequest projectRequest = new ProjectRequest("Test Project", "active", 1L);

        // 매처를 정확히 사용하여 호출
        Mockito.when(projectService.saveProject(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenReturn(project);


        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(1L))
                .andExpect(jsonPath("$.projectName").value("Test Project"))
                .andExpect(jsonPath("$.status").value("active"))
                .andExpect(jsonPath("$.adminId").value(1L));
    }

    @Test
    void testGetProject() throws Exception {
        Mockito.when(projectService.getProject(eq(1L))).thenReturn(project);

        mockMvc.perform(get("/project/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(1L))
                .andExpect(jsonPath("$.projectName").value("Test Project"))
                .andExpect(jsonPath("$.status").value("active"))
                .andExpect(jsonPath("$.adminId").value(1L));
    }

    @Test
    void testDeleteProject() throws Exception {
        Mockito.doNothing().when(projectService).deleteProject(eq(1L));

        mockMvc.perform(delete("/project/delete/1"))
                .andExpect(status().isOk());
    }
}
