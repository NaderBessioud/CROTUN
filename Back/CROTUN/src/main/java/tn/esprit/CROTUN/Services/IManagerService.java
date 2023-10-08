package tn.esprit.CROTUN.Services;

import java.util.List;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Manager;



public interface IManagerService {
	List<Manager> retrieveAllManagers();

	Manager addManager(Manager m);

	void deleteManager(Long id);

	Manager updateManager(Manager ag);

	Manager retrieveManager(Long id);
	
	Manager getManagerByUserNameAndPass(String username,String pass);
	
	Manager findByEmail(String email);
	
	Manager getManagerByUsername(String username);
	
	Manager UpdatePassword(Manager manager,String password);
	
	void updatePass(String pass,String username);
	boolean checkPass(String pass,String username);
}
