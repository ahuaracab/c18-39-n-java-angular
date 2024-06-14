package com.nocountry.docspotback.dto;


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
