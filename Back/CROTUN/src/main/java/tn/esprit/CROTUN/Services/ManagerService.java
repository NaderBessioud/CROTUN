package tn.esprit.CROTUN.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Repositories.ManagerRepository;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	ManagerRepository managerRep;
	
	@Override
	public List<Manager> retrieveAllManagers() {
		
		return (List<Manager>) managerRep.findAll();
	}

	@Override
	public Manager addManager(Manager m) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		m.setPassword(encoder.encode(m.getPassword()));
		m.setStatus(false);
		m.setEnabled(true);
		m.setCreatedAt(new Date() );
		managerRep.save(m);
		return m;
	}

	@Override
	public void deleteManager(Long id) {
		managerRep.deleteById(id);
		
	}

	@Override
	public Manager updateManager(Manager ag) {
		Manager manager=managerRep.findByEmail(ag.getEmail());
		ag.setPassword(manager.getPassword());
		ag.setStatus(true);
		ag.setEnabled(true);
		managerRep.save(ag);
		return null;
	}

	@Override
	public Manager retrieveManager(Long id) {
		
		return managerRep.findById(id).orElse(null);
	}

	@Override
	public Manager getManagerByUserNameAndPass(String username, String pass) {
	
		return managerRep.findByUserNameAndPassword(username, pass);
	}

	@Override
	public Manager findByEmail(String email) {
		
		return managerRep.findByEmail(email);
	}

	@Override
	public Manager getManagerByUsername(String username) {
		return managerRep.findByUserName(username);
	}

	@Override
	public Manager UpdatePassword(Manager manager, String password) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		manager.setPassword(encoder.encode(password));
		return manager;
	}

	@Override
	public void updatePass(String pass, String username) {
		Manager manager=managerRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		manager.setPassword(encoder.encode(pass));
		managerRep.save(manager);
		
	}

	@Override
	public boolean checkPass(String pass, String username) {
		Manager manager=managerRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String oldPass=encoder.encode(pass);
		return oldPass.equals(manager.getPassword());
	}

}
