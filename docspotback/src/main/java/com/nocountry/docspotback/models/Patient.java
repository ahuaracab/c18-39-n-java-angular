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
@Table(name = "patients")
public class Patient extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_patient")
    private UUID idPatient=UUID.randomUUID();

    @Column(name = "name_patient",length = 150,nullable = false)
    private String namePatient;

    @Column(name = "cellphone_patient",length = 10,nullable = false)
    private String cellphonePatient;

    @Column(name = "photo_patient")
    private String photoPatient;

    @Column(name = "has_social_work",nullable = false)
    private Boolean hasSocialWork;

    @Column(name = "social_work",length = 15)
    private String socialWork;

    @ManyToOne
    private User user;

    @OneToOne
    private ClinicalStory clinicalStory;
}
