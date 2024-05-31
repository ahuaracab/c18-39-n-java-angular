package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProfessionalSpecialtyPK.class)
@Entity
public class ProfessionalSpecialty {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_professional")
    private Professional professional;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
