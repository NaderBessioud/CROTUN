package tn.esprit.CROTUN.Controller;

import java.io.IOException;

import java.util.List;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import tn.esprit.*;
import tn.esprit.CROTUN.Entities.CreditCard;
import tn.esprit.CROTUN.repository.*;

import tn.esprit.CROTUN.Services.CreditCardService;
import tn.esprit.CROTUN.Services.QRCodeGenerator;




@RestController
@CrossOrigin(origins = "*")
public class CreditCardController {
	@Autowired
	QRCodeGenerator qr;
	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Autowired
	CreditCardService creditcardservice;
	@GetMapping("/retrieve-all-CreditCard")
	@ResponseBody
	public List<CreditCard> getCreditCard() {
	List<CreditCard> list = creditcardservice.retrieveAllCreditCard();
	return list;
	}
	
	@PostMapping("/add-CreditCard/{n}/{amount}/{idagent}")
	@ResponseBody
	public List<CreditCard> addCreditCard(@PathVariable int n,@PathVariable Long amount,@PathVariable Long idagent) throws WriterException, IOException
	{
		return creditcardservice.addCreditCard(n,amount,idagent);
	 
	}

	@PutMapping("/modify-CreditCard")
	@ResponseBody
	public CreditCard modifyCreditCard(@RequestBody CreditCard c) {
	return creditcardservice.updateCreditCard(c);
	}
	
	@DeleteMapping("/remove-CreditCard/{CreditCard-id}")
	@ResponseBody
	public void removeCreditCard(@PathVariable("CreditCard-id") Long CreditCardId) {
	creditcardservice.removeCreditCard(CreditCardId);
	}

	


    
    

	
}
