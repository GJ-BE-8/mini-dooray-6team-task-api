package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.domain.Tag;
import com.nhnacademy.task.domain.Task;
import com.nhnacademy.task.domain.TagTask;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TagTaskRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.request.TagTaskRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagTaskServiceImplTest {

    @Mock
    private TagTaskRepository tagTaskRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TagTaskServiceImpl tagTaskService;

    private Tag tag;
    private Task task;
    private TagTaskRequest tagTaskRequest;
    private TagTask tagTask;

    @BeforeEach
    void setUp() {
        // Tag와 Task 객체 설정
        tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("Test Tag");

        task = new Task();
        task.setTaskId(1L);
        task.setContent("Test Task");

        // TagTaskRequest 객체 설정
        tagTaskRequest = new TagTaskRequest();
        tagTaskRequest.setTagId(tag.getTagId());
        tagTaskRequest.setTaskId(task.getTaskId());

        // TagTask 객체 설정
        tagTask = new TagTask();
        tagTask.setTagTaskId(1L);
        tagTask.setTag(tag);
        tagTask.setTask(task);
    }

    @Test
    void saveTagTask() {
        // Arrange: save 메서드 호출 시 tagTask 객체를 반환하도록 설정
        when(tagRepository.findById(tagTaskRequest.getTagId())).thenReturn(java.util.Optional.of(tag));
        when(taskRepository.findById(tagTaskRequest.getTaskId())).thenReturn(java.util.Optional.of(task));
        when(tagTaskRepository.save(any(TagTask.class))).thenReturn(tagTask);

        // Act: tagTaskService의 saveTagTask 메서드 호출
        TagTask savedTagTask = tagTaskService.saveTagTask(tagTaskRequest);

        // Assert: tagTask가 저장되어 반환되는지 확인
        assertNotNull(savedTagTask);
        assertEquals(tagTask.getTagTaskId(), savedTagTask.getTagTaskId());
        assertEquals(tagTask.getTag(), savedTagTask.getTag());
        assertEquals(tagTask.getTask(), savedTagTask.getTask());

        // Mockito 검증: tagRepository, taskRepository, tagTaskRepository의 메서드가 호출되었는지 확인
        verify(tagRepository, times(1)).findById(tagTaskRequest.getTagId());
        verify(taskRepository, times(1)).findById(tagTaskRequest.getTaskId());
        verify(tagTaskRepository, times(1)).save(any(TagTask.class));
    }

    @Test
    void deleteTagTask() {
        // Arrange: deleteById 메서드 호출 시 아무 동작도 하지 않도록 설정
        doNothing().when(tagTaskRepository).deleteById(1L);

        // Act: tagTaskService의 deleteTagTask 메서드 호출
        tagTaskService.deleteTagTask(1L);

        // Assert: deleteById 메서드가 한 번 호출되었는지 확인
        verify(tagTaskRepository, times(1)).deleteById(1L);
    }
}