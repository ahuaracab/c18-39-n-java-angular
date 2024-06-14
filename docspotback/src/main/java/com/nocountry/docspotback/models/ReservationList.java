package com.nocountry.docspotback.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_list")
public class ReservationList {

	@Id
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
