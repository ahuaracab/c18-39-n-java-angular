package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id_patient")
    private UUID idPatient = UUID.randomUUID();

    @Column(name = "name_patient", length = 150, nullable = false)
    private String namePatient;

    @Column(name = "cellphone_patient", length = 12, nullable = false)
    private String cellphonePatient;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, optional = false)
    private ClinicalStory clinicalStory;



    @OneToOne
    @JsonIgnore
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations;


}
