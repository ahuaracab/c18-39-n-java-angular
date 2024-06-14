package com.nocountry.docspotback.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationListDTO {

	private UUID idReservation;
	
	private String queryIntent;
	
	private UUID idPatient;
	
	private String namePatient;
	
	private UUID idShift;
	
	private UUID idProfessional;
	
	private String nameProfessional;
	
	private String nameSpecialty;
	
	private LocalDate dateShift;
	
	private LocalTime hoursTime;
}
