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
    @Column(name = "id_history")
    private UUID idPatient=UUID.randomUUID();

    @OneToOne
    private Patient patient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicalStory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StoryDetail> details;
}
