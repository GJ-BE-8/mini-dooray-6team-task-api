package com.nhnacademy.task.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @NotNull
    private long taskId;

    @NotNull
    @Length(max = 50)
    private String writerId;

    @NotNull
    @Length(max = 255)
    private String content;
}
