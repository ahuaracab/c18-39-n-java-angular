package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.ProfessionalView;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IProfessionalRepo;
import com.nocountry.docspotback.repositories.IProfessionalSpecialtyRepo;
import com.nocountry.docspotback.repositories.IProfessionalViewRepo;
import com.nocountry.docspotback.services.IProfessionalService;

import jakarta.transaction.Transactional;

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

    @Autowired
    private IProfessionalViewRepo viewRepo;
    @Override
    protected IGenericRepo<Professional, UUID> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Professional saveTransactional(Professional professional, List<Specialty> specialties) {
        //repo.save(professional);
        System.out.println("Especialidad 1: "+specialties.get(0).getIdSpecialty());
        //System.out.println("Especialidad 2: "+specialties.get(1).getIdSpecialty());
        System.out.println("Profesional: "+professional.getIdProfessional());
        specialties.forEach(specialty ->psRepo.saveSpecialty(professional.getIdProfessional(), specialty.getIdSpecialty()));
        return professional;
    }


	@Override
	public Page<Professional> findAllProfessional(Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAllProfessional(pageable);
	}

@Override
public Page<Professional> getAllProfessionalsBySpecialityName(String nameSpecialty, Pageable pageable) {
	// TODO Auto-generated method stub
    PageRequest pageReq = PageRequest.of(0, 10,  Sort.Direction.fromString("ASC"), "value_query");

	return repo.getAllProfessionalsBySpecialityName(nameSpecialty, pageReq);
}

@Override
public List<ProfessionalView> findAllProfessionalView() {
	// TODO Auto-generated method stub
	return viewRepo.findAllProfessionalView();

}

	

}
