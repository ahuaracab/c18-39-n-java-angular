package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_specialty")
    private UUID idSpecialty=UUID.randomUUID();

    @Column(name = "name_specialty", length = 50, nullable = false)
    private String nameSpecialty;

    @Column(name = "description_specialty")
    private String descriptionSpecialty;

}
