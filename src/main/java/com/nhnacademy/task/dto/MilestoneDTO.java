package com.nhnacademy.task.dto;


import com.nhnacademy.task.domain.MileStone;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MilestoneDTO {
    private long milestoneId;
    private String milestoneName;


    public MilestoneDTO(MileStone mileStone) {
        this.milestoneId = mileStone.getMilestoneId();
        this.milestoneName = mileStone.getMilestoneName();
    }

}
