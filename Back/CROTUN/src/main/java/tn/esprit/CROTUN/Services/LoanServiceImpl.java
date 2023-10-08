package tn.esprit.CROTUN.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import tn.esprit.CROTUN.Entities.Guarantee;
import tn.esprit.CROTUN.Entities.Loan;

import tn.esprit.CROTUN.Entities.StateLoan;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.GuaranteeRepository;
import tn.esprit.CROTUN.Repositories.LoanRepository;
@Service
public class LoanServiceImpl implements ILoanService {
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	GuaranteeRepository guaranteeRepository;
	

	
	
	@Override
	public Loan addLoanRequest(Loan l,Long IdC) {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
		Guarantee g=l.getGuranteeLoan();
		l.setCustomerLoans(customerRepository.findById(IdC).orElse(null));
		l.setStateL(StateLoan.PENDING);

		loanRepository.save(l);
		l.setLoanRef(l.getIdL().toString() + l.getCustomerLoans().getFirstName().substring(0, 1)
				+ l.getCustomerLoans().getLastName().substring(0, 1) + String.format("%06d", number) );
		l.setScore(predictScore(l.getIdL()));
		if( l.getScore()>250) {
			l.setInterest(0.12);
		}else if( l.getScore()<250 && l.getScore()>230) {
			l.setInterest(0.13);
		}else  {
			l.setInterest(0.15);
		}
		l.setRemainAmount(l.getTotalAmount()-l.getPayedAmount());
		
		//loanRepository.save(l);
		guaranteeRepository.save(g);
		return l;
	}
	
	@Override
	public Loan acceptLoanRequest(Long id) {
		Loan l=loanRepository.findById(id).get();
		l.setStateL(StateLoan.ACCEPTED);
		l.setDateStartLoan(LocalDate.now());
		l.setDateEndLoan(LocalDate.now().plusMonths(l.getLoanPeriodInMonths()));
		loanRepository.save(l);
		return l;
	}
	
	@Override
	public Loan denyLoanRequest(Long id) {
		Loan l=loanRepository.findById(id).get();
		l.setStateL(StateLoan.DENIED);
		l.setArchive(true);
		loanRepository.save(l);
		return l;
	}
	
	@Override
	public void deleteLoanRequest(long id) {
		loanRepository.deleteById(id);
	}
	
	
	public Map<Object, Object> getScoreAttr(Long loanId) {
		Loan l=loanRepository.findById(loanId).get();
		HashMap<Object, Object> mapId = new HashMap<>();
		HashMap<Object, Object> map = new HashMap<>();
		//JSONObject jo = new JSONObject();
			
				map.put("age",l.getCustomerLoans().getAge());
				map.put("sex",l.getCustomerLoans().getSex());
				map.put("housing",l.getCustomerLoans().getHousing());
				map.put("savingAccount",l.getCustomerLoans().getSavingAccounts());
				map.put("amount",l.getTotalAmount());
				map.put("mois",l.getLoanPeriodInMonths());
				map.put("purpose",l.getLoanPurpose());
		
	return map;	
	}
	
	
	
	
	public double  predictScore(long id) {
		
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject("http://127.0.0.1:5000/predict/"+id, Object.class);
		 //Map<String, Object> map = oMapper.convertValue(response, Map.class);
		System.out.println(response);
	return  (double) response;
	}
	
	
	@Override
	public void affectScoreToLoans() {
		for (Loan l : retrieveAllLoans()) {
			l.setScore(predictScore(l.getIdL()));
			loanRepository.save(l);
		
	 }
	}


	@Override
	public List<Loan> retrieveAllLoans() {
		List<Loan> Loans = (List<Loan>) loanRepository.findAll();
		return Loans;
	}

	@Override
	public Loan retrieveLoan(long id) {
		return loanRepository.findById(id).orElse(null);
	}
//https://openclassrooms.com/fr/courses/5481101-calculez-et-utilisez-les-taux-d-interet/5600611-calculez-les-mensualites-constantes
	@Override
	public double calculMensualite(double capital, int mois,double interest) {
		
		double mens=0;
		mens=(capital*(interest/12))/
				(1-Math.pow((1+interest/12),-mois));
		return mens;
	}

	@Override
	public double calculMontantRestantDû(double capital, int mois,int moisM, double interest) {
		
		
		double MontantRestant;
		double interestM = capital*(interest/12);
		double amortissement = calculMensualite(capital, moisM,interest) - interestM;
		
		MontantRestant=capital;
		for (int i = 0; i < mois; i++) {
			MontantRestant = MontantRestant - amortissement;
			interestM = (interest/12) * MontantRestant;
			amortissement = calculMensualite(capital, moisM,interest) - interestM;
			
			
		}
		return MontantRestant;
	}

	@Override
	public double calculMantantInterest(double capital, double montantTotal) {
		return montantTotal - capital;
		 
	}

	@Override
	public String simulationInterest(double capital, int mois, double interest) {
		
		double mensualite = calculMensualite(capital, mois , interest) ;
		double total = mensualite * mois;
		double mantantinterest = calculMantantInterest(capital,total);
		
		return "Your loan of :  " +capital +"\n "+ "Monthly Payments :  "+ mensualite +"\n "
		+ "Interest Rate : " +interest +"\n "
				
				+"Total Interest : " +mantantinterest +"\n "+ "Total Payment : " + total ;
	}



	@Override
	public Map<Object, Object> scoreDataTransform(String sex, String housing, float savingAccount,String purpose) {
		
		HashMap<Object, Object> map = new HashMap<>();
		
		//float [] arr= new float[4];
		if (sex.equals("female")) {
			map.put("0",(float) 0.0);
		}else {
			map.put("0",(float) 1.0);
		}
		
		if (housing.equals("FREE")) {
			map.put("1",(float) 0.0);
		}else if (housing.equals("OWN")) {
			map.put("1",(float) 1.0);	
		}else if (housing.equals("RENT")) {
			map.put("1",(float) 2.0);
		}
		
		if (savingAccount<=1000) {
			map.put("2",(float) 0.0);
		}else if ((savingAccount>1000)&& (savingAccount<=4000)) {
			map.put("2",(float) 1.0);
		}else if ((savingAccount>4000)&& (savingAccount<=10000)) {
			map.put("2",(float) 3.0);
		}else if (savingAccount>10000) {
			map.put("2",(float) 2.0);
		}
		
		
		if(purpose.equals("Business")){
		map.put("3",(float) 0.0);
		}else if(purpose.equals("Car")){
			map.put("3",(float) 1.0);
		}else if(purpose.equals("DomesticAppliances")){
			map.put("3",(float) 2.0);
		}else if(purpose.equals("Education")){
			map.put("3",(float) 3.0);
		}else if(purpose.equals("Furniture_equipment")){
			map.put("3",(float) 4.0);	
		}else if(purpose.equals("Radio_TV")){
			map.put("3",(float) 5.0);
		}else if(purpose.equals("Repairs")){
			map.put("3",(float) 6.0);
		}else if(purpose.equals("Vacation_others"))
			map.put("3",(float) 7.0);


		return map;
	}








}
