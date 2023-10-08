package tn.esprit.CROTUN.Event;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.EmailVerificationToken;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Services.IEmailVerificationTokenService;

@Component
public class RegistrationListener implements  ApplicationListener<OnRegistrationCompleteEvent> {
	
	private final String FromAddress="CROTUN@gmail.com";
	private final String SenderName="CROTUN Team";
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private IEmailVerificationTokenService emailVerificationTokenService;
	


	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		try {
			this.confirmRegistration(event);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) throws UnsupportedEncodingException, MessagingException {
		String Fullname;
		Agent agent=event.getAgent();
		Customer customer=event.getCustomer();
		Manager manager=event.getManager();
		Investor investor=event.getInvestor();
		String recipientAddress;
		 String content = "Dear [[name]],<br>"
		            + "Please click the link below to verify your registration:<br>"
		            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		            + "Thank you,<br>"
		            + "Your company name.";
		 
		if(agent != null) {
			recipientAddress=agent.getEmail();
			 Fullname=agent.getFirstName()+" "+agent.getLastName();
			EmailVerificationToken emailVerificationToken=emailVerificationTokenService.createEmailVerificationToken(agent);
			content = content.replace("[[name]]",Fullname);
			String verifyURL = "http://localhost:8082/CROTUN/auth/confirmRegistration/" + emailVerificationToken.getToken();
			content = content.replace("[[URL]]", verifyURL);
			}
		else if(customer != null) {
			recipientAddress=customer.getEmail();
			 Fullname=customer.getFirstName()+" "+customer.getLastName();
			EmailVerificationToken emailVerificationToken=emailVerificationTokenService.createEmailVerificationToken(customer);
			content = content.replace("[[name]]",Fullname);
			String verifyURL = "http://localhost:8082/CROTUN/auth/confirmRegistration/" + emailVerificationToken.getToken();
			content = content.replace("[[URL]]", verifyURL);
		}
		else if(manager != null){
			recipientAddress=manager.getEmail();
			 Fullname=manager.getFirstName()+" "+manager.getLastName();
			EmailVerificationToken emailVerificationToken=emailVerificationTokenService.createEmailVerificationToken(manager);
			content = content.replace("[[name]]",Fullname);
			String verifyURL = "http://localhost:8082/CROTUN/auth/confirmRegistration/" + emailVerificationToken.getToken();
			content = content.replace("[[URL]]", verifyURL);
		}
		else {
			
			recipientAddress=investor.getEmail();
			 Fullname=investor.getFirstName()+" "+investor.getLastName();
			EmailVerificationToken emailVerificationToken=emailVerificationTokenService.createEmailVerificationToken(investor);
			content = content.replace("[[name]]",Fullname);
			String verifyURL = "http://localhost:8082/CROTUN/auth/confirmRegistration/" + emailVerificationToken.getToken();
			content = content.replace("[[URL]]", verifyURL);
		}
		
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message);
		helper.setFrom(FromAddress,SenderName);
		helper.setTo(recipientAddress);
		helper.setSubject("Email Confirmation");
		
		helper.setText(content, true);
	    
	    javaMailSender.send(message);
		
		
		
		
	}
	


}
