package com.nhnacademy.task.dto;


import com.nhnacademy.task.domain.Tag;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagDTO {
    private long tagId;
    private String tagName;

    public TagDTO(Tag tag) {
        this.tagId = tag.getTagId();
        this.tagName = tag.getTagName();
    }

}
