package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.TokenEmailException;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;
import tn.esprit.CROTUN.Repositories.PasswordTokenRepository;
import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
@Service
public class PasswordTokenService implements IPasswordTokenService {
	
	@Value("${pass.PassTokenDurationMs}")
	private long passTokenDurationMs;
	private final String FromAddress="CROTUN@gmail.com";
	private final String SenderName="CROTUN Team";
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	PasswordTokenRepository passwordTokenRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	InvestorRespository investorRespository;
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ManagerService managerService;

	@Override
	public PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException {
		PasswordResetToken passwordResetToken=new PasswordResetToken();
		Agent agent=agentRepository.findByEmail(email);
		Customer customer=customerRepository.findByEmail(email);
		Manager manager=managerRepository.findByEmail(email);
		Investor investor=investorRespository.findByEmail(email);
		passwordResetToken.setToken(UUID.randomUUID().toString());
		passwordResetToken.setExpireDate(Instant.now().plusSeconds(passTokenDurationMs));
		
		String toAddress;
		 String content = "<p>Hello,</p>"
		            + "<p>You have requested to reset your password.</p>"
		            + "<p>Copy this token to reset your password:</p>"
		            + "<p>"+passwordResetToken.getToken()+"</p>"
		            + "<br>"
		            + "<p>Ignore this email if you do remember your password, "
		            + "or you have not made the request.</p>";
		if(agent != null) {
			System.out.print(agent.getUserName()+" ism agent");
			passwordResetToken.setAgentPass(agent);
			toAddress=agent.getEmail();
			 
		}
		else if(customer != null) {
			passwordResetToken.setCustomerPass(customer);
			toAddress=customer.getEmail();
			
		}
		else if(manager != null) {
			passwordResetToken.setManagerPass(manager);
			toAddress=manager.getEmail();
		
		}
		else if(investor != null) {
			passwordResetToken.setInvestorPass(investor);
			toAddress=investor.getEmail();
			
			
		}
		else
			throw new UserNotFoundException("Could not find any user with the email"+ email);
		
		passwordTokenRepository.save(passwordResetToken);
		
		MimeMessage message = javaMailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	   
		helper.setFrom(FromAddress,SenderName);
		helper.setTo(toAddress);
		helper.setSubject("Password Reset");
		helper.setText(content, true);
	     
	    javaMailSender.send(message);
		
		return passwordResetToken;
		
	}

	@Override
	public boolean VerifyExpiration(String token) {
		PasswordResetToken passwordResetToken=passwordTokenRepository.findByToken(token);
		if(passwordResetToken !=null) {
		if(passwordResetToken.getExpireDate().isBefore(Instant.now())){
			passwordTokenRepository.delete(passwordResetToken);
			throw new TokenEmailException(token, "this token was expired");
			}
		return true;
		}
		else
			throw new TokenEmailException(token, "this token is not in the database");
	}

	@Override
	public void ConfirmPasswordReset(String token, String password) {
		PasswordResetToken passwordResetToken=passwordTokenRepository.findByToken(token);
		System.out.print(passwordResetToken);
		VerifyExpiration(token);
		Agent agent=passwordResetToken.getAgentPass();
		Customer customer=passwordResetToken.getCustomerPass();
		Manager manager=passwordResetToken.getManagerPass();
		Investor investor=passwordResetToken.getInvestorPass();
		
		if(agent != null) {
			agentService.UpdatePassword(agent, password);
		}
		else if(customer != null)
			customerService.UpdatePassword(customer, password);
		else if(manager != null)
			managerService.UpdatePassword(manager, password);
		else
			investorService.UpdatePassword(investor, password);
			
		
	}

	@Override
	public List<PasswordResetToken> getExpireToken() {
		
		return passwordTokenRepository.findExpireToken(Instant.now());
	}

	@Override
	public void deleteToken(Long id) {
		passwordTokenRepository.deleteById(id);
		
	}
	

}
