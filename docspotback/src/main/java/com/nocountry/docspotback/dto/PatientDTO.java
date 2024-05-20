package com.nocountry.docspotback.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {


    private UUID idPatient;

    private String namePatient;

    private String cellphonePatient;

    private String photoPatient;

    private Boolean hasSocialWork;

    private String socialWork;

    private UserDTO user;
}
