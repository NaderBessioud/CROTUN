package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Messages;

@Repository
public interface MessageRepository extends CrudRepository<Messages, Long> {
	@Query("SELECT m FROM Messages m WHERE ((m.customerSender= :customer OR m.customerReceiver= :customer) AND (m.agentSender=:agent OR m.agentReceiver=:agent)) ")
	List<Messages>retrieveMessagesagentclient(
	@Param("customer") Customer customer,@Param("agent") Agent agent);
	
	@Query("SELECT m FROM Messages m WHERE  ((m.agentSender=:agents AND  m.agentReceiver=:agentr) OR(m.agentSender=:agentr AND  m.agentReceiver=:agents)) ")
	List<Messages>retrieveMessagesagentagent(
	@Param("agents") Agent agents,@Param("agentr") Agent agentr);
	
	@Query( "SELECT DISTINCT (m.agentReceiver)  FROM Messages m" )
	List<Agent> retrieveconversations();
	
	
}
