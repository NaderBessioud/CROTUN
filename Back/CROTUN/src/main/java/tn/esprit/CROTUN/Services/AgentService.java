package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.AgentCard;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;

@Service
public class AgentService implements IAgentService,UserDetailsService {
	
	private static final Logger logger=LogManager.getLogger(AgentService.class);

	@Autowired
	private AgentRepository agentRep;
	
	@Autowired
	private CustomerRepository customerRep;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	InvestorRespository investorRespository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.print(username);
		Agent agent=agentRep.findByUserName(username);
		System.out.print(agent);
		Customer customer=customerRep.findByUserName(username);
		Manager manager=managerRepository.findByUserName(username);
		Investor investor =investorRespository.findByUserName(username);
		if((agent==null)&&(customer==null)&&(manager==null)&&(investor==null)) {
			logger.info("User not found");
			throw new  UsernameNotFoundException("User not found in database");
		}
		else {
			Collection<SimpleGrantedAuthority> authorites=new ArrayList<>();
			if(agent !=null) {
				logger.info("Agent found "+agent.getUserName());
			authorites.add(new SimpleGrantedAuthority("agent"));
			logger.info("Agent found "+agent.getUserName());
			return new org.springframework.security.core.userdetails.User(agent.getUserName(), agent.getPassword(), authorites);
			}
			
			else if(customer != null) {
				authorites.add(new SimpleGrantedAuthority("customer"));
				logger.info("Customer found "+customer.getUserName());
				return new org.springframework.security.core.userdetails.User(customer.getUserName(), customer.getPassword(), authorites);
			}
			else if(manager != null) {
				authorites.add(new SimpleGrantedAuthority("manager"));
			
				logger.info("Manager found "+manager.getUserName());
				return new org.springframework.security.core.userdetails.User(manager.getUserName(), manager.getPassword(), authorites);
			}
			else if(investor != null) {
				authorites.add(new SimpleGrantedAuthority("investor"));
				logger.info("investor found "+investor.getUserName());
				return new org.springframework.security.core.userdetails.User(investor.getUserName(), investor.getPassword(), authorites);
				
			}
			return null;
			
		}
			
		
	}
	
	@Override
	public List<Agent> retrieveAllAgents() {
		
		return  (List<Agent>)agentRep.findAll();
	}

	@Override
	public Agent addAgent(Agent a) throws UnsupportedEncodingException, MessagingException {
		
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		a.setPassword(encoder.encode(a.getPassword()));
		
		a.setStatus(false);
		a.setEnabled(true);
		a.setBanned(false);
		
		a.setCreatedAt(new Date());
		agentRep.save(a);
		return a;
	}

	@Override
	public void deleteAgent(Long id) {
		agentRep.deleteById(id);
		
	}

	@Override
	public Agent updateAgent(Agent ag) {
		
		Agent agent=agentRep.findByEmail(ag.getEmail());
		agent.setAddress(ag.getAddress());
		agent.setUserName(ag.getUserName());
		agent.setFirstName(ag.getFirstName());
		agent.setLastName(ag.getLastName());
		agent.setBirthDate(ag.getBirthDate());
		agent.setCIN(ag.getCIN());
		agent.setSalary(ag.getSalary());
		
		
	
		agentRep.save(agent);
		return ag;
	}

	@Override
	public Agent retrieveAgent(Long id) {
		
		return agentRep.findById(id).orElse(null);
	}

	@Override
	public Agent getAgentByUsernameAndPass(String username, String pass) {
		
		return agentRep.findByUserNameAndPassword(username, pass);
	}

	@Override
	public Agent findByEmail(String email) {
		
		return agentRep.findByEmail(email);
	}

	@Override
	public Agent findByUserName(String username) {
		return agentRep.findByUserName(username);
	}

	@Override
	public Agent UpdatePassword(Agent agent, String password) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		agent.setPassword(encoder.encode(password));
		agentRep.save(agent);
		return agent;
	}

	@Override
	public List<AgentCard> getAgentCard() {
			
		
		return agentRep.getAgentCard();
	}

	@Override
	public Agent banAgent(String username,int nbreDay) {
		Agent agent=agentRep.findByUserName(username);
		agent.setBanned(true);
		agent.setBannedPeriode(new Date(new Date().getTime()+(nbreDay*1000 * 60 * 60 * 24)));
		agentRep.save(agent);
		return agent;
	}

	@Override
	public List<Agent> getBannedAgent() {
		
		return agentRep.findByBanned(true);
	}

	@Override
	public Agent getById(Long id) {
	
		return agentRep.findById(id).orElse(null);
	}

	@Override
	public void updatePass(String pass,String username) {
		
		Agent agent= agentRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		agent.setPassword(encoder.encode(pass));
		agentRep.save(agent);
		
		
	}

	@Override
	public boolean checkPass(String pass, String username) {
		Agent agent=agentRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String oldPass=encoder.encode(pass);
		return oldPass.equals(oldPass==agent.getPassword()); 
		
	}

	@Override
	public List<Agent> getEnabledAgent() {
		return agentRep.findByEnabled(false);
	}

	

}
