package com.nocountry.docspotback.services;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nocountry.docspotback.models.Role;

public interface IRoleService extends ICRUDService<Role, UUID>{

	@Query(value = "FROM roles r WHERE r.nameRole=:roleName")
	Role findRoleByroleName(@Param("roleName")String roleName);

}
