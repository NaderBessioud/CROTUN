package tn.esprit.CROTUN.Services;

import java.util.List;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Messages;

public interface IMessageService  {
	List<Messages> retrieveAllMessagess();

	Messages addMessagesagentagent(Messages a,Long idagentsender,Long idagentreceiver);
	Messages addMessagesagentclient(Messages a,Long idagentsender,Long idclientreceiver);
	Messages addMessagesclientagent(Messages a,Long idclientsender,Long idagentreceiver);

	


	void deleteMessages(Long id);

	Messages updateMessages(Messages ag);

	Messages retrieveMessages(Long id);
	
	List<Messages> getMessagesagentagent(Long username,Long pass);
	List<Messages> getMessagesagentclient(Long username,Long pass);
	public String addChar(String str, char ch, int position) ;

	List<Agent> getConverAgents();

	Agent getAgent(Long idcust);

	List<Customer> getCustomer(Long idagent);



	
}
