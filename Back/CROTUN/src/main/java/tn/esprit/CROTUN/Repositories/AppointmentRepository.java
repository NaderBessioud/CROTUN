package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.CROTUN.Entities.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment,Long> {
	@Query(value = "SELECT *  FROM appointment a WHERE  DATEDIFF (a.date_app,NOW())=7" , nativeQuery = true)
	List<Appointment> retrievenotifappointmet();

}
