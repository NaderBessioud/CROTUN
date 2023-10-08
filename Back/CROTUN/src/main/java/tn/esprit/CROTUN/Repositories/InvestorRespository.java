package tn.esprit.CROTUN.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.AgentCard;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.InvestorInvestment;

@Repository
public interface InvestorRespository extends CrudRepository<Investor, Long>{
	
	
	
	Investor findByUserNameAndPassword(String username,String password);
	Investor findByEmail(String email);
	Investor findByUserName(String username);
	
	List<Investor> findByBanned(Boolean isBanned);

	/* @Query("SELECT new tn.esprit.CROTUN.Entities.InvestorInvestment(i.firstName,i.lastName,i1.amount) " +
	           "FROM " +
	           "    Investor i join  i.investments i1   " +
	           "ORDER BY "+
	           "i1.amount DESC ")
	List<InvestorInvestment> getTopInvestment();*/
	
	List<Investor> findByEnabled(Boolean isEnabled);
	
	

}
