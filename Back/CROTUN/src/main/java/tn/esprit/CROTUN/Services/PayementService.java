package tn.esprit.CROTUN.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.stripe.exception.StripeException;

import tn.esprit.CROTUN.Entities.Payement;
import tn.esprit.CROTUN.Entities.PaymentIntentDto;
import tn.esprit.CROTUN.Entities.reponse;

public interface PayementService {
	
	List<Payement> retrieveAllPayement();

	Payement addPayement (Payement p);
	public reponse PayeeWith_Sold_card(Long id,Long idSlice) ;
	
	Payement retrievePayement (Long id);
	
	Double addSold (Long id , String code);
	public reponse Payee_Enligne(Long id,Long idSlice,PaymentIntentDto pi) throws StripeException;
}
