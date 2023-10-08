package tn.esprit.CROTUN.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.CustomerPenality;
import tn.esprit.CROTUN.Entities.CustomerPenalityDate;
import tn.esprit.CROTUN.Repositories.CustomerRepository;


@Service
public class CustomerService implements ICustomerService {

	
	@Autowired
	CustomerRepository customerRep;
	
	
	@Override
	public List<Customer> retrieveAllCustomers() {
		
		return (List<Customer>) customerRep.findAll();
	}

	@Override
	public Customer addCustomer(Customer c) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		c.setPassword(encoder.encode(c.getPassword()));
		c.setStatus(false);
		c.setEnabled(true);
		c.setBanned(false);
		
		c.setCreatedAt(new Date());
		
		customerRep.save(c);
		return c;
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRep.deleteById(id);
		
	}

	@Override
	public Customer updateCustomer(Customer u) {
		Customer customer=customerRep.findByEmail(u.getEmail());
		u.setPassword(customer.getPassword());
		u.setStatus(true);
		u.setEnabled(true);
		u.setBanned(false);
		
		customerRep.save(u);
		return u;
	}

	@Override
	public Customer retrieveCustomer(Long id) {
		
		return customerRep.findById(id).orElse(null);
	}

	@Override
	public Customer getCustomerByUsernameAndPass(String username,String pass) {
		
		return customerRep.findByUserNameAndPassword(username, pass);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRep.findByEmail(email);
	}

	@Override
	public Customer getCustomerByusername(String username) {
		return customerRep.findByUserName(username);
	}

	@Override
	public Customer UpdatePassword(Customer customer, String password) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		customer.setPassword(encoder.encode(password));
		return customer;
	}

	@Override
	public List<CustomerPenality> getPenalityByCustomer() {
		List<CustomerPenality> RCustomerPenalities=new ArrayList<>();
		List<CustomerPenality> CustomerPenalities= customerRep.getPenalityByCustomer();
		CustomerPenalities=CustomerPenalities.stream().sorted((c2,c1)->c1.getPenality().compareTo(c2.getPenality())).collect(Collectors.toList());
		for(int i=0;i<3;i++) {
			RCustomerPenalities.add(CustomerPenalities.get(i));
		}
		return RCustomerPenalities;
		
	}

	@Override
	public String getTextFromImage(String file) {
		String result="";
		File image = new File(file);
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C:\\CroTUN_Microfinance\\CROTUN\\src\\main\\resources\\tessdata");
		tesseract.setLanguage("eng");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		
		try {
			 result = tesseract.doOCR(image);
		} catch (TesseractException e) {
			System.out.print(e.getMessage());
		}
		return result;
	}

	@Override
	public Customer banCustomer(String username, int nbr) {
		Customer customer=customerRep.findByUserName(username);
		customer.setBanned(true);
		
		customer.setBannedPeriode(new Date(new Date().getTime()+(nbr*1000 * 60 * 60 * 24)));
		customerRep.save(customer);
		return customer;
	}

	@Override
	public List<Customer> getBannedCustomer() {
		return customerRep.findByBanned(true);
	}

	@Override
	public Customer getById(Long id) {
		return customerRep.findById(id).orElse(null);
	}

	@Override
	public void updatePass(String pass, String username) {
		Customer customer=customerRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		customer.setPassword(encoder.encode(pass));
		customerRep.save(customer);
		
		
	}

	@Override
	public boolean checkPass(String pass, String username) {
		Customer customer=customerRep.findByUserName(username);
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		String oldPass=encoder.encode(pass);
		return oldPass.equals(customer.getPassword());
		
	}

	@Override
	public List<CustomerPenalityDate> getPenalityByCustomerByDate(Long id) {
		
		return customerRep.getPenalityByCustomerByDate(id);
	}

	@Override
	public List<Customer> findByEnabled() {
		
		return customerRep.findByEnabled(false);
	}
	

}
