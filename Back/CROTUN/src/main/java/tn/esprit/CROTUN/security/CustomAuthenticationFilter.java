package tn.esprit.CROTUN.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Event.MySimpleUrlAuthenticationSuccessHandler;
import tn.esprit.CROTUN.Services.AgentService;
import tn.esprit.CROTUN.Services.CustomerService;
import tn.esprit.CROTUN.Services.InvestorService;
import tn.esprit.CROTUN.Services.ManagerService;



@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter{
	
	//@Value("${jwt.header}")
    private String tokenRequestHeader="Authorization";
	
	//@Value("${jwt.prefix}")
    private String tokenRequestPrefix="Bearer";
    private static final Logger logger=LogManager.getLogger(CustomAuthenticationFilter.class);
	
	
    
	public CustomAuthenticationFilter() {
		
		// TODO Auto-generated constructor stub
	}





	@Autowired
	 private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	 private AgentService agentService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private InvestorService investorService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=getTokenFromRequest(request);
		System.out.print("----------------->"+token);
		
		if((StringUtils.isNotBlank(token) && jwtTokenProvider.isTokenValid(token))) {

			String username=jwtTokenProvider.getSubjectFromToken(token);
			
			UserPrincipal principal;
			
			if(agentService.findByUserName(username)!= null) {
				Agent ag=agentService.findByUserName(username);
				 principal=new UserPrincipal(ag);
			}
			else if(managerService.getManagerByUsername(username) != null) {
				Manager ma=managerService.getManagerByUsername(username);
				principal=new UserPrincipal(ma);
			}
			else if(investorService.findByUserName(username) != null) {
				principal=new UserPrincipal(investorService.findByUserName(username));
			}
			else {
				Customer cs=customerService.getCustomerByusername(username);
				principal=new UserPrincipal(cs);
			}
			System.out.print("----------------->"+principal.getAuthorities());
			
			//UsernamePasswordAuthenticationToken auth=jwtTokenProvider.getAuthentication(principal.getUsername(),  principal.getAuthorities(),request);
			UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,null,principal.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		}
		
		filterChain.doFilter(request, response);
		}
	
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken="";
		
		try {
		 bearerToken=request.getHeader(tokenRequestHeader);
		 System.out.print("----------------->"+bearerToken);
		
		if((StringUtils.isNotBlank(bearerToken)) && bearerToken.startsWith(tokenRequestPrefix)) {
			bearerToken=bearerToken.replace(tokenRequestPrefix, "");
			
			return bearerToken;
		}
		
		}catch (NullPointerException ex) {
			
			System.out.print(ex.getCause());
			// TODO: handle exception
		}
		return null;
	}
	
}
