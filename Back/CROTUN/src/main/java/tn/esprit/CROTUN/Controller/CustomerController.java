package tn.esprit.CROTUN.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.CustomerPenality;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
import tn.esprit.CROTUN.Services.DeviceService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	IPasswordTokenService passwordTokenService;
	
	@GetMapping("/getCustomer/{id}")
	@ResponseBody
	public Customer retrieveCustomer(@PathVariable("id") Long CusÎd) {
	return customerService.retrieveCustomer(CusÎd);
	}
	
	@PutMapping("/updateCustomer")
	@ResponseBody
	public Customer updateCustomer(@RequestBody Customer Customer) {
		try {
			customerService.updateCustomer(Customer);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return Customer;
	}
	
	@PutMapping("/updatePass")
	public void upadatePass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		customerService.updatePass(pass, username);
	}
	
	@GetMapping("/checkPass")
	@ResponseBody
	public boolean checkPass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		return customerService.checkPass(pass, username);
	}
	
	@DeleteMapping("/deleteCustomer/{idA}")
	@ResponseBody
	public ResponseEntity DeleteCustomer(@PathVariable("idA") Long idCustomer) {
		try {
			Customer customer=customerService.getById(idCustomer);
			if((customer.getAccountCustomer() != null) || (customer.getLoansCust() != null) || (customer.getAvailbilitys() != null)
					||(customer.getCardsCust() != null) || (customer.getComplaints() != null))
				return ResponseEntity.ok("cannot delete this customer");
			customerService.deleteCustomer(idCustomer);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return ResponseEntity.ok("customer deleted");
		
	}
	
	
	
	@PutMapping("/banCustomer/{username}/{nbr}")
	@ResponseBody
	public Customer banCustomer(@PathVariable("username") String username,@PathVariable("nbr") int nbr) {
		return customerService.banCustomer(username, nbr);
		
	}
	
	@GetMapping("/resetPasswordRequest/{email}")
	@ResponseBody
	public PasswordResetToken generatePassToken(@RequestParam("email") String email) {
		try {
			return passwordTokenService.CreatePasswordToken(email);
		} catch (UnsupportedEncodingException |MessagingException e) {
			System.out.print(e.getMessage());
		}catch(UserNotFoundException ex) {
			System.out.print(ex.getMessage());
		}
		return null;
	}
	
	
	@GetMapping("/resetPassword/{token}/{pass}")
	public void resetPasswod(@PathVariable("token") String token,@PathVariable("pass") String pass) {
		passwordTokenService.ConfirmPasswordReset(token, pass);
	}
	
	@GetMapping("/getCustomerByUsername")
	@ResponseBody
	public Customer getCustomerbyUsername(@RequestParam("user") String user) {
		return customerService.getCustomerByusername(user);
	}
	@GetMapping("/getEnabledCustomer")
	@ResponseBody
	public List<Customer> getEnabledCustomer(){
		return customerService.findByEnabled();
	}

	
	

}
