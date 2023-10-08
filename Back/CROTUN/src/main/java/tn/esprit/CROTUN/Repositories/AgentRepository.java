package tn.esprit.CROTUN.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.AgentCard;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
	Agent findByUserNameAndPassword(String username,String password);
	Agent findByEmail(String email);
	Agent findByUserName(String username);
	
	  @Query("SELECT new tn.esprit.CROTUN.Entities.AgentCard(a.firstName,a.lastName,count(c.Amount),c.Amount) " +
	           "FROM " +
	           "    Agent a join  a.cards c  where c.State=false "+
	           "GROUP BY " +
	           " c.Amount , a.firstName,a.lastName" )
	List<AgentCard> getAgentCard();
	  
	List<Agent> findByBanned(Boolean isBanned);
	
	List<Agent> findByEnabled(Boolean isEnabled);

}
