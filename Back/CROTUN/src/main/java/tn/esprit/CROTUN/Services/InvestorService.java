package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.InvestorInvestment;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.InvestmentRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;

@Service
public class InvestorService implements IInvestorService {
	
	private static final Logger logger=LogManager.getLogger(AgentService.class);
	
	@Autowired
	InvestorRespository investorRespository;
	
	@Autowired
	InvestmentRepository investmentRepository;
	
	
	
	
	@Override
	public List<Investor> retrieveAllInvestor() {
		
		return (List<Investor>) investorRespository.findAll();
	}

	@Override
	public Investor addInvestor(Investor a) throws UnsupportedEncodingException, MessagingException {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		a.setPassword(encoder.encode(a.getPassword()));
		a.setStatus(false);
		a.setEnabled(true);
		a.setBanned(false);
		
		a.setCreatedAt(new Date());
		investorRespository.save(a);
		return a;
	}

	@Override
	public void deleteinvestor(Long id) {
		investorRespository.deleteById(id);
		
	}

	@Override
	public Investor updateInvestor(Investor ag) {
		Investor investor=investorRespository.findByEmail(ag.getEmail());
		ag.setPassword(investor.getPassword());
		ag.setStatus(true);
		ag.setEnabled(true);
		ag.setBanned(false);
		investorRespository.save(ag);
		return ag;
	}

	@Override
	public Investor retrieveInvestor(Long id) {
		
		return investorRespository.findById(id).orElse(null);
	}

	@Override
	public Investor getAgentByUsernameAndPass(String username, String pass) {
		
		return investorRespository.findByUserNameAndPassword(username, pass);
	}

	@Override
	public Investor findByEmail(String email) {

		return investorRespository.findByEmail(email);
	}

	@Override
	public Investor findByUserName(String username) {
		
		return investorRespository.findByUserName(username);
	}

	@Override
	public Investor UpdatePassword(Investor investor, String password) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		investor.setPassword(encoder.encode(password));
		investorRespository.save(investor);
		return investor;
	}

	@Override
	public Investor banInvestor(String username, int nbr) {
		Investor investor=investorRespository.findByUserName(username);
		investor.setBanned(true);
		investor.setBannedPeriode(new Date(new Date().getTime()+(nbr*1000 * 60 * 60 * 24)));
		investorRespository.save(investor);
		return investor;
	}

	@Override
	public List<InvestorInvestment> getTopInvestment() {
		
		List<InvestorInvestment> investments=new ArrayList<>();
		for(Investment investment:investmentRepository.findTop3ByOrderByAmountDesc()) {
			investments.add(new InvestorInvestment(investment.getInvestors().getFirstName(), investment.getInvestors().getLastName(), investment.getAmount()));
			
		}
		return investments;
	}

	@Override
	public List<Investor> getBannedInvestor() {
		return investorRespository.findByBanned(true);
	}

	@Override
	public void updatePass(String pass, String username) {
		Investor investor=investorRespository.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		investor.setPassword(encoder.encode(pass));
		investorRespository.save(investor);
		
		
	}

	@Override
	public boolean checkPass(String pass, String username) {
		Investor investor=investorRespository.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String oldPass=encoder.encode(pass);
		
		return oldPass.equals(investor.getPassword());
	}

	@Override
	public List<Investor> findByEnabled(Boolean isEnabled) {
		
		return investorRespository.findByEnabled(false);
	}

}
