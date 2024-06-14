package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IShiftRepo;
import com.nocountry.docspotback.services.IShiftService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftServiceImpl extends CRUDImpl<Shift, UUID> implements IShiftService {

    @Autowired
    private IShiftRepo repo;

    @Override
    protected IGenericRepo<Shift, UUID> getRepo() {
        return repo;
    }


	@Transactional
	@Override
	public void newShiftForProfessional(UUID idProfessional, UUID idShift) {
		// TODO Auto-generated method stub
		repo.newShiftForProfessional(idProfessional, idShift);
	}


	@Override
	public List<Shift> availableShiftsByProfessional(UUID idProfessional, LocalDate dateShift) {
		// TODO Auto-generated method stub
		return repo.availableShiftsByProfessional(idProfessional, dateShift);
	}


}
