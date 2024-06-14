package com.nocountry.docspotback.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalFilterDTO {

	private UUID idProfessional;
	
    @NotNull
    @Schema(example = "Pablo Matos")
    private String nameProfessional;

    @NotNull
    private Double reputation;

    private String urlPhoto;
    
    @NotNull
    @Schema(example = "79.9")
    private Double valueQuery;

    @NotNull
    @Schema(example = "754159wf")
    private String mp;
    
    private UUID idSpecialty;

    private String nameSpecialty;

}
