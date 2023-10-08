package tn.esprit.CROTUN.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Messages;
import tn.esprit.CROTUN.Entities.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
	@Query("SELECT n FROM Notification n WHERE (n.agentNotif=:agent)")
	List<Notification>getnotifagents(
	@Param("agent") Agent agent);
	
	@Query("SELECT n FROM Notification n WHERE (n.customerNotif=:client)")
	List<Notification>getnotifcliens(
	@Param("client") Customer client);

	

}
