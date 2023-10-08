package tn.esprit.CROTUN.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.AgentAmount;
import tn.esprit.CROTUN.Entities.AgentCard;
import tn.esprit.CROTUN.Entities.CardAmount;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.CustomerPenality;
import tn.esprit.CROTUN.Entities.CustomerPenalityDate;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.InvestorInvestment;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
import tn.esprit.CROTUN.Services.DeviceService;
import tn.esprit.CROTUN.Services.IAgentService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IInvestorService;
import tn.esprit.CROTUN.Services.IManagerService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	IManagerService managerService;
	
	@Autowired
	IAgentService agentService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	IPasswordTokenService passwordTokenService;
	
	@Autowired
	IInvestorService investorService;
	
	
	
	@PutMapping("/updateManager")
	@ResponseBody
	public Manager updateManager(@RequestBody Manager Manager) {
		try {
			managerService.updateManager(Manager);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return Manager;
	}
	
	@DeleteMapping("/deleteManager/{idA}")
	@ResponseBody
	public void DeleteManager(@PathVariable("idA") Long idManager) {
		try {
			managerService.deleteManager(idManager);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	
	
	

	
	@GetMapping("/resetPasswordRequest/{email}")
	@ResponseBody
	public PasswordResetToken generatePassToken(@RequestParam("email") String email) {
		try {
			return passwordTokenService.CreatePasswordToken(email);
		} catch (UnsupportedEncodingException |MessagingException e) {
			System.out.print(e.getMessage());
		}catch(UserNotFoundException ex) {
			System.out.print(ex.getMessage());
		}
		return null;
	}
	
	
	@GetMapping("/resetPassword/{token}/{pass}")
	public void resetPasswod(@PathVariable("token") String token,@PathVariable("pass") String pass) {
		passwordTokenService.ConfirmPasswordReset(token, pass);
	}
	
	
	
	@GetMapping("/getTopInvestment")
	@ResponseBody
	public List<InvestorInvestment> getTopInvestment(){
		return investorService.getTopInvestment();
				}
	
	@GetMapping("/getPenality")
	public List<CustomerPenality> getPenalityByCustomer() {
		return customerService.getPenalityByCustomer();
		
	}
	
	@GetMapping("/getAgentCards")
	@ResponseBody
	public List<AgentAmount> getCards(){
		List<AgentAmount> result=new ArrayList<AgentAmount>();
		
		List<AgentCard> agCard=agentService.getAgentCard();
		System.out.print("-------->hay card lkbira "+agCard.get(0).getFirstName());
		System.out.print("-------->hay card lkbira "+agCard.get(0).getNbre());
		List<CardAmount> ca=new ArrayList<CardAmount>();
		String fullname="";
		
		for(int i=0;i<agCard.size();i++) {
			 ca=new ArrayList<CardAmount>();
			 fullname=agCard.get(i).getFirstName()+" "+agCard.get(i).getLastName();
			 ca.add(new CardAmount(Float.toString(agCard.get(i).getAmount()), agCard.get(i).getNbre()));
			result.add(new AgentAmount(fullname, ca));
			
		}
		
		
		return result;
	}
	// @MessageMapping("/hello")
	//    @SendTo("/topic/greetings")
	  //  public Greeting greeting(HelloMessage message) throws Exception {
	       // Thread.sleep(1000); // simulated delay
	      //  return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  //  }

	@PutMapping("/updatePass")
	public void upadatePass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		managerService.updatePass(pass, username);
	}
	
	@GetMapping("/checkPass")
	@ResponseBody
	public boolean checkPass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		return managerService.checkPass(pass, username);
	}
	
	@GetMapping("/getManagerByid")
	@ResponseBody
	public Manager getManagerbyUsername(@RequestParam("user") String user) {
		return managerService.findByEmail(user);
	}
	
	
	@GetMapping("/getPenalityByCustomerByDate")
	@ResponseBody
	public List<CustomerPenalityDate> getPenalityByCustomerByDate(@RequestParam("id") Long id){
		return customerService.getPenalityByCustomerByDate(id);
	}

}
