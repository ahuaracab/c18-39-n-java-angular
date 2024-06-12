package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Shift;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

public interface IShiftService extends ICRUDService<Shift, UUID>{
	
	void newShiftForProfessional(UUID idProfessional,UUID idShift);

	List<Shift> availableShiftsByProfessional(@Param("idProfessional") UUID idProfessional,@Param("dateShift") LocalDate dateShift);

}
