package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.InvestmentOffer;


@Repository
public interface AvailablityRepository extends JpaRepository<Availablity, Long> {

	
	@Query("SELECT i FROM Availablity i WHERE i.customerAvailbility IS NULL")
	List<Availablity>retrieveCurrentAvailbility();
	
	
	@Query(value = "SELECT * FROM availablity WHERE customer_availbility_idc=:customer_availbility_id " , nativeQuery = true)
	List<Availablity> retrieveCustomerAvailblity(@Param("customer_availbility_id") Long customer_availbility_id);
	
	@Query(value = "SELECT * FROM availablity WHERE agent_availbility_ida=:agent_availbility_id AND customer_availbility_idc IS NOT NULL" , nativeQuery = true)
	List<Availablity> retrieveAgentAvailblity(@Param("agent_availbility_id") Long agent_availbility_id);

	@Query(value = "SELECT * FROM availablity WHERE agent_availbility_ida=:agent_availbility_id AND customer_availbility_idc IS NULL" , nativeQuery = true)
	List<Availablity> retrieveAgentFreeApoint(@Param("agent_availbility_id") Long agent_availbility_id);
	


	@Query(value = "SELECT date_ava FROM availablity WHERE customer_availbility_idc IS NULL" , nativeQuery = true)
    List<String> dateAvai();
}
