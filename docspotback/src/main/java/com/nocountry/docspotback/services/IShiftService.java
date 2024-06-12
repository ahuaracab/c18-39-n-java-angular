package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Shift;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

public interface IShiftService extends ICRUDService<Shift, UUID>{
	List<Shift>findAllShiftByProfessionalID(UUID id);
	
	void newShiftForProfessional(UUID idProfessional,UUID idShift);

}
