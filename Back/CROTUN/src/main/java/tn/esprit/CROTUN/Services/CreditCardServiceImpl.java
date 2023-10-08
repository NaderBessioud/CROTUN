package tn.esprit.CROTUN.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.CreditCard;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.repository.*;

@Service

public class CreditCardServiceImpl  implements CreditCardService {

	@Autowired
	CreditCardRepository creditcardrepository;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	QRCodeGenerator Qr;
	@Override
	public List<CreditCard> retrieveAllCreditCard() {
		
		return (List<CreditCard>) creditcardrepository.findAll() ;
	}

	@Override
	public List<CreditCard> addCreditCard(int n,Long amount,Long idagent) throws WriterException, IOException {
		List<CreditCard> ccc = new ArrayList<>();
		Agent aa = agentRepository.findById(idagent).orElse(null);
		
		
		for (int i = 0; i < n; i++) {
			UUID code=UUID.randomUUID();
		   
		   CreditCard c1=new CreditCard();
		   
			
			c1.setCode(code.toString());
			c1.setAmount(amount);
			c1.setAgentCard(aa);
			c1 = creditcardrepository.save(c1);
			ccc.add(c1);
			
			Qr.generateQRCodeImage(c1.getIdCCard());
			
		}
		
		return ccc;
	}

	@Override
	public CreditCard updateCreditCard(CreditCard c) {
		creditcardrepository.save(c);
		return c;
	}

	@Override
	public CreditCard retrieveCreditCard(Long id) {
		CreditCard cc= creditcardrepository.findById(id).orElse(null);
		return cc ;
	}

	@Override
	public void removeCreditCard(Long id) {
		creditcardrepository.deleteById(id);
		
	}

}

