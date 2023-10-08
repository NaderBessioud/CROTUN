package tn.esprit.CROTUN.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import tn.esprit.CROTUN.Entities.PaymentIntentDto;
@Service
public class PaymentServiceStrip {
	  @Value("${stripe.key.secret}")
	    String secretKey;

	  public PaymentIntent paymentIntent(PaymentIntentDto paymentIntent) throws StripeException {
	        Stripe.apiKey = secretKey;
	        List<String> paymentMethodTypes = new ArrayList();
	        paymentMethodTypes.add("card");
	        Map<String, Object> params = new HashMap<>();
	        params.put("amount", paymentIntent.getAmount()*100);
	        params.put("currency", paymentIntent.getCurrency());
	      
	        params.put("payment_method_types", paymentMethodTypes);
	       
	        return PaymentIntent.create(params);
	    }

	    public PaymentIntent confirm(String id) throws StripeException {
	        Stripe.apiKey = secretKey;
	        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
	        Map<String, Object> params = new HashMap<>();
	        params.put("payment_method", "pm_card_visa");
	        paymentIntent.confirm(params);
	        
	        
	        return paymentIntent;
	    }

	    public PaymentIntent cancel(String id) throws StripeException {
	        Stripe.apiKey = secretKey;
	        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
	        paymentIntent.cancel();
	        return paymentIntent;
	    }
}
