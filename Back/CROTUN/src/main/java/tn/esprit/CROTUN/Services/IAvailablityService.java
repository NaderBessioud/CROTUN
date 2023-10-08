package tn.esprit.CROTUN.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tn.esprit.CROTUN.Entities.Availablity;

public interface IAvailablityService {

	void addAvailablity(Availablity Avai,LocalTime time1, LocalTime time2,LocalDate date,Long id);

	void addAvaiCustomer(Long idA, Long idC);
	List<Availablity> retrieveAllAvailablity();
	List<Availablity> retrieveCustomerAvai(Long id);
	List<Availablity> retrieveAgentAvai(Long id);
	void deleteAvai();

	List<String> dateAvai();

	List<Availablity> retrieveAgentFreeApoint(Long id);

}
