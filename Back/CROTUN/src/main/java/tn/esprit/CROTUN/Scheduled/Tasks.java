package tn.esprit.CROTUN.Scheduled;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.EmailVerificationToken;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Services.AgentService;
import tn.esprit.CROTUN.Services.IAgentService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IEmailVerificationTokenService;
import tn.esprit.CROTUN.Services.IInvestorService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;



@Configuration
public class Tasks {
	
	private static final Logger logger = LogManager.getLogger(Tasks.class);
	
	@Autowired
	private IAgentService agentService;
	
	@Autowired
	private IInvestorService investorService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IEmailVerificationTokenService emailVerificationTokenService;
	
	@Autowired
	private IPasswordTokenService passwordTokenService;
	
	@Scheduled(cron = "${cron.Token}")
	   public void DisableUserBan() {
		logger.info("From Scheduled Disable bean");
		
		for(Agent agent:agentService.getBannedAgent()) {
			if(agent.getBannedPeriode().after(new Date()) || agent.getBannedPeriode().compareTo(new Date())==0) {
				agent.setBanned(false);
				agentService.updateAgent(agent);
				logger.info("Disable ban for agent "+agent.getUserName());
			}
		}
		
		for(Customer customer:customerService.getBannedCustomer()) {
			if(customer.getBannedPeriode().before(new Date()) || customer.getBannedPeriode().compareTo(new Date())==0) {
				customer.setBanned(false);
				customerService.updateCustomer(customer);
				logger.info("Disable ban for customer "+customer.getUserName());
			}
			
			for(Investor investor:investorService.getBannedInvestor()) {
				if(investor.getBannedPeriode().before(new Date()) || investor.getBannedPeriode().compareTo(new Date())==0) {
					investor.setBanned(false);
					investorService.updateInvestor(investor);
					logger.info("Disable ban for investor "+investor.getUserName());
				}
			}
		}
	
	   }
	
	@Scheduled(cron = "${cron.ban}")
	public void ClearExpireToken() {
		for(EmailVerificationToken emailVerificationToken:emailVerificationTokenService.getExpireToken()) {
			emailVerificationTokenService.deleteToken(emailVerificationToken.getIdEmailToken());
			logger.info("Email Token deleted");
		}
		
		for(PasswordResetToken passwordResetToken:passwordTokenService.getExpireToken()) {
			passwordTokenService.deleteToken(passwordResetToken.getIdPasswordToken());
			logger.info("Password Token deleted");
		}
		
	}

}
