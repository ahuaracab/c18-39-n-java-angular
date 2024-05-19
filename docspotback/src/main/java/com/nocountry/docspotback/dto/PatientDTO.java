package com.nocountry.docspotback.dto;


import com.nocountry.docspotback.models.User;

import java.util.UUID;

public class PatientDTO {


    private UUID idPatient;

    private String namePatient;

    private String cellphonePatient;

    private String photoPatient;

    private Boolean hasSocialWork;

    private String socialWork;

    private UserDTO user;
}
