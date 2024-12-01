package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.request.TagRequest;
import com.nhnacademy.task.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTag() throws Exception {

        TagRequest tagRequest = new TagRequest(1L, "New Tag");
        Project project = new Project(1L, "Test Project", "Active", 100L);
        Tag tag = new Tag(1L, "New Tag", project);

        when(tagService.addTagToProject(1L, "New Tag")).thenReturn(tag);

        mockMvc.perform(post("/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tagId").value(1L))
                .andExpect(jsonPath("$.tagName").value("New Tag"))
                .andExpect(jsonPath("$.project.projectId").value(1L))
                .andExpect(jsonPath("$.project.projectName").value("Test Project"))
                .andExpect(jsonPath("$.project.status").value("Active"))
                .andExpect(jsonPath("$.project.adminId").value(100L));

        verify(tagService, times(1)).addTagToProject(1L, "New Tag");
    }

    @Test
    void deleteTag() throws Exception {

        doNothing().when(tagService).deleteTag(1L);

        mockMvc.perform(delete("/tags/1"))
                .andExpect(status().isOk());

        verify(tagService, times(1)).deleteTag(1L);
    }

    @Test
    void getTag() throws Exception {

        Project project = new Project(1L, "Test Project", "Active", 100L);
        Tag tag = new Tag(1L, "New Tag", project);

        when(tagService.getTagById(1L)).thenReturn(tag);

        mockMvc.perform(get("/tags/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tagId").value(1L))
                .andExpect(jsonPath("$.tagName").value("New Tag"))
                .andExpect(jsonPath("$.project.projectId").value(1L))
                .andExpect(jsonPath("$.project.projectName").value("Test Project"))
                .andExpect(jsonPath("$.project.status").value("Active"))
                .andExpect(jsonPath("$.project.adminId").value(100L));

        verify(tagService, times(1)).getTagById(1L);
    }
}
