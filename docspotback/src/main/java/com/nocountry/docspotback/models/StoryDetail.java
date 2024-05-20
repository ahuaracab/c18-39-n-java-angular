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
@Table(name = "story_details")
public class StoryDetail extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_detail")
    private UUID idDetail=UUID.randomUUID();

    @Column(nullable = false)
    private String descriptionDetail;

    @Column(nullable = false)
    private String recipe;

    @ManyToOne
    private ClinicalStory clinicalStory;
}
