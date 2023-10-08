package tn.esprit.CROTUN.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Greeting;
import tn.esprit.CROTUN.Entities.Messages;
import tn.esprit.CROTUN.Entities.Notification;
import tn.esprit.CROTUN.Repositories.NotificationRepository;
import tn.esprit.CROTUN.Services.IAgentService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IMessageService;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/messages")

public class MessagesController {
	@Autowired
	IMessageService messageservice;
	@Autowired
	ICustomerService clientserv;
	@Autowired
	IAgentService agentserv;
	@Autowired
	NotificationRepository notrep;
	
	
	
	
	@PostMapping("/addMessageagentagent")
	@ResponseBody
	public Messages addmessagesagentagent(@RequestBody Messages message,@RequestParam("agentsid") Long agentsid,@RequestParam("agentrid") Long agentrid) {
      messageservice.addMessagesagentagent(message, agentsid, agentrid);		
		
		
		return message;
	}

	@PostMapping("/addMessageagentclient")
	@ResponseBody
	public Messages addmessagesagentclient(@RequestBody Messages message,@RequestParam("agentsid") Long agentsid,@RequestParam("clientrid") Long clientrid) {
		

      messageservice.addMessagesagentclient(message, agentsid, clientrid);		
		
		
		return message;
	}

	@PostMapping("/addMessageclientagent")
	@ResponseBody
	public Messages addmessagesclientagent(@RequestBody Messages message,@RequestParam("clientsid") Long clientsid,@RequestParam("agentrid") Long agentrid) {
		String file = "C:/Users/yassi/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/CROTUN/src/main/resources/hhh.txt";
		String sl= new String();
sl="";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            int a=0;
            while ((line = br.readLine()) != null) {
            	System.out.println(line);

            	if (message.getContenu().contains(line)) {
            		int s=line.length();
            	for(int i=0;i<s;i++)
sl+='*';            	}
            message.setContenu(  message.getContenu().replace(line,sl));    
        	sl="";


            	}
            System.out.println(message.getContenu());

            
            
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
if((message.getContenu().equals("changement"))&& (agentserv.retrieveAgent(agentrid).getStatutt().equals("0")))
{
	Notification n =new Notification();
n.setDateEnvoi(new Date());
n.setContenu("votre demande de changement d agent est prise en compte");
n.setCustomerNotif(clientserv.retrieveCustomer(clientsid));
notrep.save(n);

	
	


}
      messageservice.addMessagesclientagent(message, clientsid, agentrid);
      
		if (agentserv.retrieveAgent(agentrid).getStatutt().equals("0"))
		{Messages m =new Messages();
		System.out.println("truuuuuuuuuuuuueeeeeeeeeee");
		m.setContenu("L agent "+agentserv.retrieveAgent(agentrid).getFirstName()+"est indisponible cette periode si vous ecrivez changement dans un message un autre agent va vous aider");
		messageservice.addMessagesagentclient(m, agentrid, clientsid)	;
			
		}
		
		return message;
	}
	@DeleteMapping("/deleteMessage")
	@ResponseBody
	public void DeleteMessage(@RequestParam("idM") Long idMessage) {
			messageservice.deleteMessages(idMessage);}
	
	
	
	@GetMapping("/affichermessagesagenagent")
	@ResponseBody
	public List<Messages> affichermessagesaa(@RequestParam("idadgent1") Long idagent1,@RequestParam("idadgent2") Long idagent2) {
		return messageservice.getMessagesagentagent(idagent1, idagent2);
			
	}
	
	@GetMapping("/affichermessagesclientagent")
	@ResponseBody
	public List<Messages> affichermessagesac(@RequestParam("idclient") Long idclient,@RequestParam("idagent") Long idagent) {
		return messageservice.getMessagesagentclient(idclient, idagent);
			
	}
	
	@GetMapping("/afficherconver")
	@ResponseBody
	public List<Agent> afficherconver() {
		return messageservice.getConverAgents();
			
	}
	@GetMapping("/afficherconveruser")
	@ResponseBody
	public Agent afficherconveruser(@RequestParam("iduser") Long id) {
		return messageservice.getAgent(id);
			
	}
	@GetMapping("/afficherlistcustomers")
	@ResponseBody
	public List<Customer> afficherlistcustomers(@RequestParam("idagent") Long id) {
		return messageservice.getCustomer(id);
			
	}
	
	
	
	

}
