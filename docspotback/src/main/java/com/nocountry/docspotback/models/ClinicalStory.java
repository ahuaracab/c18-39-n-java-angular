package com.nocountry.docspotback.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinical_stories")
public class ClinicalStory extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_clinical_story")
    private UUID idClinicalStory = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicalStory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StoryDetail> details;

    public UUID getIdClinicalStory() {
        return idClinicalStory;
    }

    public void setIdClinicalStory(UUID idClinicalStory) {
        this.idClinicalStory = idClinicalStory;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<StoryDetail> getDetails() {
        return details;
    }

    public void setDetails(List<StoryDetail> details) {
        this.details = details;
    }
}
