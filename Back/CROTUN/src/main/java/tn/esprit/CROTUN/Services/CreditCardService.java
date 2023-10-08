package tn.esprit.CROTUN.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.google.zxing.WriterException;
import com.stripe.exception.StripeException;

import tn.esprit.CROTUN.Entities.CreditCard;
import tn.esprit.CROTUN.Entities.PaymentIntentDto;


public interface CreditCardService {
	

	List<CreditCard> retrieveAllCreditCard();

	public List<CreditCard> addCreditCard(int n,Long amount,Long idagent) throws WriterException, IOException ;

	CreditCard updateCreditCard (CreditCard c);

	CreditCard retrieveCreditCard (Long id);

	void removeCreditCard (Long id);
	


}
