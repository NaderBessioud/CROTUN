package tn.esprit.CROTUN.Controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.AgentCard;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.JwtRequest;
import tn.esprit.CROTUN.Entities.JwtResponse;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
import tn.esprit.CROTUN.Services.AgentService;
import tn.esprit.CROTUN.Services.DeviceService;
import tn.esprit.CROTUN.Services.IAgentService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IManagerService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;
import tn.esprit.CROTUN.security.JWTTokenProvider;
import tn.esprit.CROTUN.security.UserPrincipal;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent")

public class AgentController {

	@Autowired
	IAgentService agentService;
	
	@Autowired
	ICustomerService custService;
	
	@Autowired
	IManagerService managerService;
	
	@Autowired
	JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	IPasswordTokenService passwordTokenService;
	

	@GetMapping("/getAgent/{id}")
	@ResponseBody
	public Agent retrieveOperateur(@PathVariable("id") Long agentÎd) {
	return agentService.retrieveAgent(agentÎd);
	}

	
	
	
	
	
	
	@PutMapping("/updateAgent")
	@ResponseBody
	public Agent updateAgent(@RequestBody Agent agent) {
		try {
			agentService.updateAgent(agent);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return agent;
	}
	
	@PutMapping("/upadatePass")
	public void updatePass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		agentService.updatePass(pass, username);
	}
	
	@GetMapping("/checkPass")
	@ResponseBody
	public boolean checkPass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		return agentService.checkPass(pass, username);
	}
	
	@DeleteMapping("/deleteAgent/{idA}")
	@ResponseBody
	public void DeleteAgent(@PathVariable("idA") Long idAgent) {
		try {
			Agent agent=agentService.getById(idAgent);
			if((agent.getCards() != null) || (agent.getAccountsAgent()!= null) || (agent.getAppointmentAgent() != null) ||
					(agent.getAvailbilityAgent() != null) || (agent.getComplaintAgent() != null) || (agent.getLoansAgent() != null))
				throw new Exception("Cannot delete this agent");
			agentService.deleteAgent(idAgent);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		
	}
	
	@GetMapping("/getAgentByusername")
	@ResponseBody
	public Agent getAgentbyUsername(@RequestParam("username") String user) {
		return agentService.findByUserName(user);
	}
	
	@GetMapping("/getEnabledAgent")
	@ResponseBody
	public List<Agent> getEnabledAgent(){
		return agentService.getEnabledAgent();
	}
	
	
	



	
}	
	
	
	
	
	

