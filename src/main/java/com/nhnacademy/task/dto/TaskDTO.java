package com.nhnacademy.task.dto;

import com.nhnacademy.task.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    private long taskId;
    private String content;

    public TaskDTO(Task task) {
        this.taskId = task.getTaskId();
        this.content = task.getContent();
    }
}
