package tn.esprit.CROTUN.Services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Entities.RefreshToken;
import tn.esprit.CROTUN.Exception.TokenRefreshException;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;
import tn.esprit.CROTUN.Repositories.RefreshTokenRepository;
import tn.esprit.CROTUN.security.UserPrincipal;

@Service
public class RefreshTokenService implements IRefreshTokenService {
	
	
	@Value("${jwt.refreshTokenDurationMs}")
	private Long refreshTokenDurationMs;
	
	@Autowired
	RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	InvestorRespository investorRespository;

	@Override
	public Optional<RefreshToken> getByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	@Override
	public RefreshToken CreateRefreshToken(UserPrincipal principal) {
		
		String username=principal.getUsername();
		//DeleteByUser(username);
		Agent Ag=agentRepository.findByUserName(username);
		Customer customer=customerRepository.findByUserName(username);
		Manager manager=managerRepository.findByUserName(username);
		Investor investor=investorRespository.findByUserName(username);
		RefreshToken refreshToken=new RefreshToken();
		if(Ag != null) 
			refreshToken.setAgentToken(Ag);
		else if(customer != null) {
			refreshToken.setCustomerToken(customer);
	
		}
		else if(manager != null)
			refreshToken.setManagerToken(manager);
		else if(investor != null)
			refreshToken.setInvestorToken(investor);
		refreshToken.setExpireDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		
		
		refreshToken=refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	@Override
	public boolean VerifyExpiration(RefreshToken token) {
		if(token.getExpireDate().isBefore(Instant.now())) {
		
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
			
		}
		
		return true;
	}

	@Override
	public int DeleteByUser(String username	) {
		Agent Ag=agentRepository.findByUserName(username);
		Customer customer=customerRepository.findByUserName(username);
		Manager manager=managerRepository.findByUserName(username);
		Investor investor=investorRespository.findByUserName(username);
		if(Ag != null) 
			return refreshTokenRepository.deleteByAgent(Ag.getIdA());
		else if(customer != null)
			return refreshTokenRepository.deleteByCustomer(customer.getIdC());
		else if(manager != null)
			return refreshTokenRepository.deleteByManager(manager.getIdM());
		else if(investor != null)
			return refreshTokenRepository.deleteByInvestor(investor.getIdInvestor());
		return 0;
	}

}
