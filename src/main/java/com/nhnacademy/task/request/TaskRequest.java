package com.nhnacademy.task.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private long taskId;

    @NotNull
    private long projectId;
    @NotNull
    @Length(min = 1, max = 300)
    private String content;

    private long mileStoneId;
}
