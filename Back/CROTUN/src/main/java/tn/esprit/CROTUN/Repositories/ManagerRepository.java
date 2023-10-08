package tn.esprit.CROTUN.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
	Manager findByUserNameAndPassword(String username,String password);
	Manager findByEmail(String email);
	Manager findByUserName(String username);
}
