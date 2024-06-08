package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Shift;

import java.util.List;
import java.util.UUID;

public interface IShiftService extends ICRUDService<Shift, UUID>{
	List<Shift>findAllShiftByProfessionalID(UUID id);

}
