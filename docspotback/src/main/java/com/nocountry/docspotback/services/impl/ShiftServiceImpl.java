package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IShiftRepo;
import com.nocountry.docspotback.services.IShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<Shift> findAllShiftByProfessionalID(UUID id) {
		// TODO Auto-generated method stub
		return repo.findAllShiftByProfessionalID(id);
	}
}
