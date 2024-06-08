package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Shift;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

public interface IShiftRepo extends IGenericRepo<Shift, UUID>{
	
	@Query(value = "SELECT s.id_shift,s.id_deleted,s.repeat_shift,s.state_shift,s.created_at,s.deleted_at,s.updated_At,s.date_shift,s.hours_time FROM shifts s INNER JOIN professionals_shifts ps ON ps.id_shift=s.id_shift WHERE ps.id_professional=:id",nativeQuery = true)
	List<Shift>findAllShiftByProfessionalID(UUID id);
}
