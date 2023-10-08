package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.InvestorInvestment;

public interface IInvestorService {
	
	List<Investor> retrieveAllInvestor();

	Investor addInvestor(Investor a) throws UnsupportedEncodingException, MessagingException;

	void deleteinvestor(Long id);

	Investor updateInvestor(Investor ag);

	Investor retrieveInvestor(Long id);
	
	Investor getAgentByUsernameAndPass(String username,String pass);
	
	Investor findByEmail(String email);
	
	Investor findByUserName(String username);
	
	Investor UpdatePassword(Investor agent,String password);
	
	Investor banInvestor(String username,int nbr);
	
	List<InvestorInvestment> getTopInvestment();
	
	List<Investor> getBannedInvestor();
	
	void updatePass(String pass,String username);
	boolean checkPass(String pass,String username);
	List<Investor> findByEnabled(Boolean isEnabled);

}
