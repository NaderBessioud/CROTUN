package tn.esprit.CROTUN.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Messages;
import tn.esprit.CROTUN.Repositories.AccountRepository;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.MessageRepository;
@Service
public class MessagesService implements IMessageService {
@Autowired
MessageRepository messagesrep;
@Autowired
AgentRepository agentsrep;
@Autowired
CustomerRepository custsrep;
@Autowired
AccountRepository accrep;


	@Override
	public List<Messages> retrieveAllMessagess() {
		return (List<Messages>) messagesrep.findAll();
	}

	
	@Override
	public Messages addMessagesagentagent(Messages a,Long idagentsender,Long idagentreceiver) {
		Agent agents=agentsrep.findById(idagentsender).orElse(null);
		Agent agentr=agentsrep.findById(idagentreceiver).orElse(null);
		a.setAgentSender(agents);
		a.setAgentReceiver(agentr);
		 Date date= new Date();
		a.setDateEnvoi(date);
		messagesrep.save(a);
		
		return a;
	}

	@Override
	public Messages addMessagesagentclient(Messages a ,Long idagentsender,Long idclientreceiver) {
		
		Agent agents=agentsrep.findById(idagentsender).orElse(null);
		
		Customer custrr=custsrep.findById(idclientreceiver).orElse(null);
		System.out.print(custrr.getIdC());

		a.setAgentSender(agents);
		a.setCustomerReceiver(custrr);
		Date date=new Date();

		a.setDateEnvoi(date);
		messagesrep.save(a);
		
		return a;	}

	@Override
	public Messages addMessagesclientagent(Messages a, Long idclientsender, Long idagentreceiver) {
		Customer custss=custsrep.findById(idclientsender).orElse(null);
		Agent agentr=agentsrep.findById(idagentreceiver).orElse(null);
		a.setAgentReceiver(agentr);
		a.setCustomerSender(custss);
		LocalDateTime instance = LocalDateTime.now();
		Date date=new Date();

		a.setDateEnvoi(date);
		messagesrep.save(a);
		
		return a;
	}

	@Override
	public void deleteMessages(Long id) {
           messagesrep.deleteById(id);		
	}

	@Override
	public Messages updateMessages(Messages ag) {
		Date date=new Date();

		ag.setDateEnvoi(date);
		messagesrep.save(ag);
		
		return ag;
	}

	@Override
	public Messages retrieveMessages(Long id) {

Messages m=messagesrep.findById(id).orElse(null);		
return m;
	}

	@Override
	public List<Messages> getMessagesagentagent(Long idagents, Long idagentr) {
		Agent agents=agentsrep.findById(idagents).orElse(null);
		Agent agentr=agentsrep.findById(idagentr).orElse(null);

		return messagesrep.retrieveMessagesagentagent(agents, agentr);
		
	}

	@Override
	public List<Messages> getMessagesagentclient(Long cust, Long agent) {
Customer custt=custsrep.findById(cust).orElse(null);
Agent agents=agentsrep.findById(agent).orElse(null);



		return messagesrep.retrieveMessagesagentclient(custt, agents);
	}
	public String addChar(String str, char ch, int position) {
	    return str.substring(0, position) + ch + str.substring(position);
	}
@Override
public List <Agent> getConverAgents()
{List<Agent> lida=messagesrep.retrieveconversations();
System.out.println(lida.get(0));


return lida;	






}
@Override
public  Agent getAgent(Long idcust)
{Customer c= custsrep.findById(idcust).orElse(null);
Agent g=c.getAccountCustomer().getAgentAccount();

return g;	






}

@Override
public  List<Customer> getCustomer(Long idagent)
{Agent g= agentsrep.findById(idagent).orElse(null);
List<Customer> lc= accrep.retrieveconver(g);
return lc;	






}


	
	

}
