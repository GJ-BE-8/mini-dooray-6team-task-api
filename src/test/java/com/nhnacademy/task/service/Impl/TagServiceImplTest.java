package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Project;
import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TagTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagTaskRepository tagTaskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private Project project;
    private Tag tag;

    @BeforeEach
    void setUp() {
        // Mock 객체 설정
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setStatus("Active");
        project.setAdminId(100L);

        tag = new Tag();
        tag.setTagName("Test Tag");
        tag.setProject(project);
    }

    @Test
    void testAddTagToProject() {
        // Arrange
        Long projectId = 1L;
        String tagName = "New Tag";

        // Mockito가 projectRepository.findById를 호출할 때 반환할 값을 지정
        when(projectRepository.findById(projectId)).thenReturn(java.util.Optional.of(project));
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        // Act
        Tag savedTag = tagService.addTagToProject(projectId, tagName);

        // Assert
        assertNotNull(savedTag);
        assertEquals("Test Tag", savedTag.getTagName());
        assertEquals(1L, savedTag.getProject().getProjectId());

        // Mockito 검증
        verify(projectRepository, times(1)).findById(projectId);
        verify(tagRepository, times(1)).save(any(Tag.class));
    }

    @Test
    void testDeleteTag() {
        // Arrange
        long tagId = 1L;

        // Act
        tagService.deleteTag(tagId);

        // Assert
        verify(tagTaskRepository, times(1)).deleteByTag_TagId(tagId);
        verify(tagRepository, times(1)).deleteById(tagId);
    }
}
