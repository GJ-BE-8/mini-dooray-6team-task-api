package com.nhnacademy.task.request;

import jakarta.validation.constraints.NotBlank;
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
public class ProjectRequest {
    @Length(max = 50)
    @NotBlank
    private String projectName;
    @NotBlank
    @Length(max = 50)
    private String status;
    @NotNull
    private Long adminId;
}
