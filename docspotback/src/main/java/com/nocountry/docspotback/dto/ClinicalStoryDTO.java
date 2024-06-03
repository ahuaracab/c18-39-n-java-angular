package com.nocountry.docspotback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.StoryDetail;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalStoryDTO {

    private UUID idPatient;
    
   
    private PatientDTO patient;

    
    private List<StoryDetailDTO> details;
}
