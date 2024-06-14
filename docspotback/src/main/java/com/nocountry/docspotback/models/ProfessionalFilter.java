package com.nocountry.docspotback.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professional_filter")
public class ProfessionalFilter {

	@Id
	private UUID idProfessional;
	
    private String nameProfessional;

    @NotNull
    private Double reputation;

    private String urlPhoto;
    
    private Double valueQuery;

    private String mp;
    
    private UUID idSpecialty;
    
    private String nameSpecialty;

}
