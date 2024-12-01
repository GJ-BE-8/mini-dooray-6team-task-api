package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.ProjectMember;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProjectMemberControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProjectMemberController controller;

    @Mock
    private ProjectMemberRepository repository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void addMemberProject() throws Exception {
        ProjectMember request = new ProjectMember(1L, 1L);
        when(repository.addProjectMember(anyLong(), anyLong()))
                .thenReturn(Collections.singletonList(1L));

        mockMvc.perform(post("/project-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1L));

        verify(repository, times(1)).addProjectMember(1L, 1L);
    }

    @Test
    void deleteMemberProject() throws Exception {
        ProjectMember request = new ProjectMember(1L, 1L);
        doNothing().when(repository).deleteProjectMember(anyLong(), anyLong());

        mockMvc.perform(delete("/project-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(repository, times(1)).deleteProjectMember(1L, 1L);
    }

    @Test
    void getMemberProject() throws Exception {
        when(repository.getProjectMember(anyLong()))
                .thenReturn(Arrays.asList(1L, 2L));

        mockMvc.perform(get("/project-members/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1L))
                .andExpect(jsonPath("$[1]").value(2L));

        verify(repository, times(1)).getProjectMember(1L);
    }
}
