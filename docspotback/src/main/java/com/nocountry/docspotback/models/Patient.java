package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column(name = "name_patient", length = 150, nullable = false)
    private String namePatient;

    @Column(name = "cellphone_patient", length = 12, nullable = false)
    private String cellphonePatient;

    @Column(name = "photo_patient")
    private String photoPatient;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ClinicalStory clinicalStory;

    @Column(name = "has_social_work", nullable = false)
    private Boolean hasSocialWork;

    @Column(name = "social_work", length = 15)
    private String socialWork;

    @ManyToOne
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations;
}
