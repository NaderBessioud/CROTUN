package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.AgentCard;





public interface IAgentService {
	List<Agent> retrieveAllAgents();

	Agent addAgent(Agent a) throws UnsupportedEncodingException, MessagingException;

	void deleteAgent(Long id);

	Agent updateAgent(Agent ag);

	Agent retrieveAgent(Long id);
	
	Agent getAgentByUsernameAndPass(String username,String pass);
	
	Agent findByEmail(String email);
	
	Agent findByUserName(String username);
	
	Agent UpdatePassword(Agent agent,String password);
	
	List<AgentCard> getAgentCard();
	
	Agent banAgent(String username,int nbreDay);
	
	List<Agent> getBannedAgent();
	
	Agent getById(Long id);
	
	void updatePass(String pass,String username);
	boolean checkPass(String pass,String username);
	
	List<Agent> getEnabledAgent();
}
