package com.nocountry.docspotback.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ProfessionalSpecialtyPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "id_professional")
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;
}
