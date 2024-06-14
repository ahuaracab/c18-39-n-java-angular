package com.nocountry.docspotback.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nocountry.docspotback.models.Role;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.IRoleRepo;
import com.nocountry.docspotback.services.IRoleService;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, UUID> implements IRoleService{
	
	@Autowired
	private IRoleRepo repo;

	@Override
	protected IGenericRepo<Role, UUID> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Role findRoleByroleName(String roleName) {
		// TODO Auto-generated method stub
		return repo.findRoleByroleName(roleName);
	}

}
