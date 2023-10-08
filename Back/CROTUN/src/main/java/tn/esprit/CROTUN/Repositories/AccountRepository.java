package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.CROTUN.Entities.Account;
import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;

public interface AccountRepository extends CrudRepository<Account, Long> {
	@Query("SELECT (m.customerAccount) FROM Account m WHERE (m.agentAccount)=:agent")
	List<Customer>retrieveconver(@Param("agent") Agent agent);
}
