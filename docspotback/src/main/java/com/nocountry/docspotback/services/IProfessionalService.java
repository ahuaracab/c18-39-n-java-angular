package com.nocountry.docspotback.services;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Specialty;

import java.util.List;
import java.util.UUID;

public interface IProfessionalService extends ICRUDService<Professional, UUID>{
    Professional saveTransactional(Professional professional, List<Specialty> specialties);

}
