package tn.esprit.CROTUN.Services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import tn.esprit.CROTUN.Entities.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.EmailVerificationToken;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.EmailVerificationTokenRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;

import tn.esprit.CROTUN.Exception.TokenEmailException;

@Service
public class EmailVerificationTokenService implements IEmailVerificationTokenService {
	
	@Value("${email.emailTokenDurationMs}")
	private Long emailTokenDurationMs;
	@Autowired
	EmailVerificationTokenRepository EMVRep;
	@Autowired
	AgentRepository agentRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	InvestorRespository investorRespository;

	@Override
	public EmailVerificationToken createEmailVerificationToken(Object user) {
		EmailVerificationToken EMV=new EmailVerificationToken();
		EMV.setToken(UUID.randomUUID().toString());
		EMV.setExpireDate(Instant.now().plusSeconds(emailTokenDurationMs));
		if(user instanceof Agent)
			EMV.setAgentEmail(agentRepository.findByUserName(((Agent) user).getUserName()));
		else if(user instanceof Customer)
			EMV.setCustomerEmail(customerRepository.findByUserName(((Customer) user).getUserName()));
		else if(user instanceof Manager)
			EMV.setManagerEmail(managerRepository.findByUserName(((Manager)user).getUserName()));
		else if(user instanceof Investor)
			EMV.setInvestorEmail(investorRespository.findByUserName(((Investor) user).getEmail()));
		EMVRep.save(EMV);
		return EMV;
	}

	
	private boolean VerifyExpiration(EmailVerificationToken token) {
		if(token.getExpireDate().isBefore(Instant.now())) {
			EMVRep.delete(token);
			throw new TokenEmailException(token.getToken(), "this token was expired");
		}
		
		return true;
	}

	@Override
	public void ConfirmUserRegistration(String Token) {
		EmailVerificationToken emailVerificationToken=EMVRep.findByToken(Token);
		Agent agent=emailVerificationToken.getAgentEmail();
		Customer customer=emailVerificationToken.getCustomerEmail();
		Manager manager=emailVerificationToken.getManagerEmail();
		Investor investor=emailVerificationToken.getInvestorEmail();
		this.VerifyExpiration(emailVerificationToken);
		if(agent != null) {
			
				
				agent.setStatus(true);
				agentRepository.save(agent);
		}
		else if(customer != null) {
			
				customer.setStatus(true);
				customerRepository.save(customer);
		}
		else if (manager != null) {
			
				manager.setStatus(true);
				managerRepository.save(manager);
			}
		else {
			investor.setStatus(true);
			investorRespository.save(investor);
		}
			
				}


	@Override
	public List<EmailVerificationToken> getExpireToken() {
		
		return EMVRep.findExpireToken(Instant.now());
	}


	@Override
	public void deleteToken(Long id) {
		EMVRep.deleteById(id);
		
	}

}




