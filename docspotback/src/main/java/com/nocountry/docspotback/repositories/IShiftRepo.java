package com.nocountry.docspotback.repositories;

import com.nocountry.docspotback.models.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IShiftRepo extends IGenericRepo<Shift, UUID>{
	
	@Query(value = "SELECT s.id_shift,s.id_deleted,s.repeat_shift,s.state_shift,s.created_at,s.deleted_at,s.updated_At,s.date_shift,s.hours_time FROM shifts s INNER JOIN professionals_shifts ps ON ps.id_shift=s.id_shift WHERE ps.id_professional=:id",nativeQuery = true)
	List<Shift>findAllShiftByProfessionalID(UUID id);
	
	@Modifying
	@Query(value = "INSERT INTO professionals_shifts(id_professional,id_shift) VALUES(:idProfessional,:idShift)",nativeQuery = true)
	void newShiftForProfessional(@Param("idProfessional") UUID idProfessional,@Param("idShift") UUID idShift);
	
	@Query(value = "SELECT s.id_shift,s.date_shift,s.hours_time,s.repeat_shift,s.state_shift,s.created_at,s.deleted_at,s.updated_At,s.is_deleted FROM shifts s \r\n"
			+ "	INNER JOIN professionals_shifts ps \r\n"
			+ "	ON ps.id_shift=s.id_shift \r\n"
			+ "	WHERE s.state_shift=true and ps.id_professional=:idProfessional and s.date_shift=:dateShift and s.date_shift>=current_date and s.hours_time>current_time\r\n"
			+ "	ORDER BY s.date_shift ASC",nativeQuery = true)
	List<Shift> availableShiftsByProfessional(@Param("idProfessional") UUID idProfessional,@Param("dateShift") LocalDate dateShift);
}
