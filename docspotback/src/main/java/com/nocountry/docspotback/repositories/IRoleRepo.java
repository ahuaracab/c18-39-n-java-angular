package com.nocountry.docspotback.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nocountry.docspotback.models.Role;

public interface IRoleRepo extends IGenericRepo<Role, UUID>{

	@Query(value = "SELECT * FROM roles r INNER JOIN user_role ur ON ur.id_role=r.id_role INNER JOIN users u ON u.id_user=ur.id_user WHERE u.id_user=:id",nativeQuery = true)
	List<Role>findAllRolesByUserId(@Param("id")UUID id);
	
	@Query(value = "SELECT * FROM roles r WHERE r.name_role=:roleName",nativeQuery = true)
	Role findRoleByroleName(@Param("roleName")String roleName);
}
