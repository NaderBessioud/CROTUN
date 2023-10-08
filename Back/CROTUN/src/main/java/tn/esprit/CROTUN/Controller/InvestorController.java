package tn.esprit.CROTUN.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.InvestorInvestment;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
import tn.esprit.CROTUN.Services.DeviceService;
import tn.esprit.CROTUN.Services.IInvestorService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/investor")
public class InvestorController {

	
	@Autowired
	IInvestorService investorService;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	IPasswordTokenService passwordTokenService;
	
	
	@PutMapping("/banInvestor/{username}/{nbr}")
	@ResponseBody
	public Investor banInvestor(@PathVariable("username") String username,@PathVariable("nbr") int nbr) {
		return investorService.banInvestor(username, nbr);
		
	}
	
	
	@PutMapping("/updateInvestor")
	@ResponseBody
	public Investor updateInvestor(@RequestBody Investor investor) {
		try {
			investorService.updateInvestor(investor);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return investor;
	}
	
	
	@PutMapping("/updatePass")
	public void upadatePass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		investorService.updatePass(pass, username);
	}
	
	@GetMapping("/checkPass")
	@ResponseBody
	public boolean checkPass(@RequestParam("pass") String pass,@RequestParam("username") String username) {
		return investorService.checkPass(pass, username);
	}
	@DeleteMapping("/deleteinvestor/{idA}")
	
	public void Deleteinvestor(@PathVariable("idA") Long idinvestor) {
		try {
			investorService.deleteinvestor(idinvestor);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
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
	
	@GetMapping("/getInvestorByusername")
	@ResponseBody
	public Investor getInvestorbyUsername(@RequestParam("username") String user) {
		return investorService.findByUserName(user);
	}

}
