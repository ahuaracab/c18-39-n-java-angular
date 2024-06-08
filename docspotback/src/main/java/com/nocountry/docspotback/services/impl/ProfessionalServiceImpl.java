package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IProfessionalRepo;
import com.nocountry.docspotback.repositories.IProfessionalSpecialtyRepo;
import com.nocountry.docspotback.services.IProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessionalServiceImpl extends CRUDImpl<Professional, UUID> implements IProfessionalService {

    @Autowired
    private IProfessionalRepo repo;

    @Autowired
    private IProfessionalSpecialtyRepo psRepo;

    @Override
    protected IGenericRepo<Professional, UUID> getRepo() {
        return repo;
    }

    @Override
    public Professional saveTransactional(Professional professional, List<Specialty> specialties) {
        repo.save(professional);
        specialties.forEach(specialty ->psRepo.saveSpecialty(professional.getIdProfessional(), specialty.getIdSpecialty()));
        return professional;
    }

   /* @Override
    public Page<Professional> getAllProfessionalsBySpecialityName(String nameSpecialty, int numPage, int pageSize, String orderBy, String sort) {
        Sort.Direction direction;
        try {
            direction = Sort.Direction.fromString(sort);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sort direction. Must be ASC or DESC", e);
        }
        if (nameSpecialty == null || nameSpecialty.isEmpty()) {
            throw new IllegalArgumentException("Specialty name must not be null or empty");
        }

        Pageable pageable = PageRequest.of(numPage, pageSize, direction, orderBy);
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            throw new IllegalArgumentException("Page and size must be non-negative");
        }

        return repo.getAllProfessionalsBySpecialityName(nameSpecialty, pageable);
    }*/

	@Override
	public Page<Professional> findAllProfessional(Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAllProfessional(pageable);
	}

@Override
public Page<Professional> getAllProfessionalsBySpecialityName(String nameSpecialty, Pageable pageable) {
	// TODO Auto-generated method stub
	return repo.getAllProfessionalsBySpecialityName(nameSpecialty, pageable);
}



}
