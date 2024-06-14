package com.nocountry.docspotback.models;

import java.util.UUID;

import org.hibernate.annotations.View;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professional_view")
@View(query = "SELECT p.id_professional, p.url_photo, p.mp, p.name_professional, p.value_query, p.reputation, p.name_specialty FROM professional_view p")
public class ProfessionalView {
	@Id
	private UUID idProfessional;
	

    private String nameProfessional;

    private Double reputation;

    private String urlPhoto;
    

    private Double valueQuery;


    private String mp;
    
    private String nameSpecialty;
	
}
