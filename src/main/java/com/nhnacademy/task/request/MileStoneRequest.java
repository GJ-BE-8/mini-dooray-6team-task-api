package com.nhnacademy.task.request;


import com.nhnacademy.task.domain.MileStone;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MileStoneRequest {
    @NotNull
    private long projectId;

    @NotNull
    private MileStone mileStone;

}
