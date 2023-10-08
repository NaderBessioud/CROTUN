package tn.esprit.CROTUN.Services;

import java.util.List;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import tn.esprit.CROTUN.Entities.CreditCard;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Payement;
import tn.esprit.CROTUN.Entities.PaymentIntentDto;
import tn.esprit.CROTUN.Entities.Slice;
import tn.esprit.CROTUN.Entities.reponse;
import tn.esprit.CROTUN.Repositories.SliceRepository;
import tn.esprit.CROTUN.repository.CrRepo;
import tn.esprit.CROTUN.repository.CreditCardRepository;

import tn.esprit.CROTUN.repository.PayementRepository;


@Service
public class PayementServiceImpl implements PayementService {
	
	@Autowired
	PayementRepository PR;
	@Autowired
	CreditCardRepository CCR;
	@Autowired
	CrRepo CR;
	@Autowired
	SliceRepository SR;
	@Autowired
	tn.esprit.CROTUN.Repositories.LoanRepository LR;
	
	@Autowired
	PaymentServiceStrip pss;

	@Override
	public List<Payement> retrieveAllPayement() {
		
		return (List<Payement>) PR.findAll() ;
	}

	@Override
	public Payement addPayement(Payement p ) {
		
		PR.save(p);
		return p;
	}

	@Override
	public Payement retrievePayement(Long id) {
		Payement pay=PR.findById(id).orElse(null);
		return pay;
	}

	@Override
	public Double addSold(Long id ,String code) {
      for (CreditCard card : CCR.findAll()) {
			
			if( code.equals(card.getCode()))
			{
				if(card.getState()==true) {
					
				Customer c =CR.findById(id).orElse(null);
				c.setSold(card.getAmount()+c.getSold());
				card.setState(false);
				CCR.save(card);
				CR.save(c);
				return c.getSold();
			
				}
			}
		
				
      }
			
      
		return Double.valueOf(null);
	}
	
	@Override
	public reponse PayeeWith_Sold_card(Long id,Long idSlice) {
		
		Customer customer =CR.findById(id).orElse(null);
		Slice slice = SR.findById(idSlice).orElse(null);
			 
			if(slice.getPrice()<=customer.getSold())
		{
			customer.setSold(customer.getSold()- slice.getPrice());
				slice.setVerified(true);
		        
				SR.save(slice);
				CR.save(customer);
				
				
				return new reponse("Payement Slice " + slice.getIdS()+" Success");  
			}
		
		return new reponse("no sold");
	}

	
	
	public reponse Payee_Enligne(Long id,Long idSlice,PaymentIntentDto pi) throws StripeException {
		
		Customer customer =CR.findById(id).orElse(null);
		Slice slice = SR.findById(idSlice).orElse(null);
		if(slice.getVerified()== false) {
		if (pi.getAmount() == slice.getPrice()) {
			
			PaymentIntent p = pss.paymentIntent(pi);
			//customer.setSold(customer.getSold()- slice.getPrice());
			slice.setVerified(true);
	        
			SR.save(slice);
			 pss.confirm(p.getId());
			
			return new reponse("Payement Slice " + slice.getIdS()+" success");  
		}
			}
				else return new reponse("Slice est deja payee");
		
		return new reponse("verifier insifisant");
		
		
	
	}
}
