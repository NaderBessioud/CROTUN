package tn.esprit.CROTUN.Services;

import java.util.Date;
import java.util.List;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.CustomerPenality;
import tn.esprit.CROTUN.Entities.CustomerPenalityDate;
import tn.esprit.CROTUN.Entities.Investor;



public interface ICustomerService {
	List<Customer> retrieveAllCustomers();

	Customer addCustomer(Customer c);

	void deleteCustomer(Long id);

	Customer updateCustomer(Customer u);

	Customer retrieveCustomer(Long id);
	
	Customer getCustomerByUsernameAndPass(String username,String pass);
	
	Customer findByEmail(String email);
	
	List<CustomerPenality> getPenalityByCustomer();

	
	Customer getCustomerByusername(String username);
	Customer UpdatePassword(Customer customer,String password);
	String getTextFromImage(String file);
	
	Customer banCustomer(String username,int nbr);
	
	List<Customer> getBannedCustomer();
	
	Customer getById(Long id);
	void updatePass(String pass,String username);
	boolean checkPass(String pass,String username);
	
	public List<CustomerPenalityDate> getPenalityByCustomerByDate(Long id);
	List<Customer> findByEnabled();
}
