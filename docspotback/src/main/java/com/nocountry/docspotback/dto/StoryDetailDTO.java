package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoryDetailDTO {
    private UUID idDetail;

    @NotNull
    private String descriptionDetail;

    @NotNull
    private String recipe;

    @NotNull
    @JsonIgnore
    private ClinicalStoryDTO clinicalStory;
}
