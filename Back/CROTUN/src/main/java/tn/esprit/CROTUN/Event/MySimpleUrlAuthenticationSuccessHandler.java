package tn.esprit.CROTUN.Event;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Services.DeviceService;

@Component
public class MySimpleUrlAuthenticationSuccessHandler  {
	private static final Logger logger = LogManager.getLogger(MySimpleUrlAuthenticationSuccessHandler.class);
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	

	/*@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			logger.info("Goooooooooooolllllll");
		 	loginNotification(authentication, request);
		
	}*/
	
	/*@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		logger.info("Goooooooooooolllllll");
	 	//loginNotification(authResult, request);
	}*/
	

	
	 public void loginAgentNotification(Agent agent, 
		      HttpServletRequest request) {
		
		 
		 
		        try {
		            
		            	deviceService.verifyAgentDevice(agent, request); 
		           
		            	
		        } catch(Exception e) {
		            logger.error("An error occurred verifying device or location");
		            throw new RuntimeException(e);
		        }
		    }
	 
	 
	 public void loginInvestorNotification(Investor investor, 
		      HttpServletRequest request) {
		
		 logger.info("hana houni");
		 
		        try {
		            
		            	deviceService.verifyInvestorDevice(investor, request); 
		           
		            	
		        } catch(Exception e) {
		            logger.error("An error occurred verifying device or location");
		            throw new RuntimeException(e);
		        }
		    }
	 
	 
	 public void loginCustomerNotification(Customer customer, 
		      HttpServletRequest request) {
		
		 
		 
		        try {
		            
		            	deviceService.verifyCustomerDevice(customer, request);
		           
		            	
		        } catch(Exception e) {
		            logger.error("An error occurred verifying device or location");
		            throw new RuntimeException(e);
		        }
		    }
	 
	 public void loginManagerNotification(Manager manager, 
		      HttpServletRequest request) {
		
		
		 
		        try {
		            
		            	deviceService.verifyManagerDevice(manager, request);
		           
		            	
		        } catch(Exception e) {
		            logger.error("An error occurred verifying device or location");
		            throw new RuntimeException(e);
		        }
		    }

}
