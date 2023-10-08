package tn.esprit.CROTUN.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Services.ILoanService;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	ILoanService loanService;
	
	// http://localhost:8089/SpringMVC/loan/retrieve-all-loans
	@GetMapping("/retrieve-all-loans")
	@ResponseBody
	public List<Loan> getLoans() {
	List<Loan> listLoans = loanService.retrieveAllLoans();
	System.out.println("test");
	return listLoans;
	}

	
	@GetMapping("/retrieve-loan/{loan-id}")
	@ResponseBody
	public Loan retrieveLoan(@PathVariable("loan-id") Long loanId) {
	return loanService.retrieveLoan(loanId);
	
	}
	
	@RequestMapping(value="/add-loan/{customer-id}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Loan addLoan(@RequestBody Loan o,@PathVariable("customer-id") Long IdC)
	{
	Loan loan = loanService.addLoanRequest(o,IdC);
	return loan;
	}
	
	@DeleteMapping("/remove-loan/{loan-id}")
	@ResponseBody
	public void removeLoan(@PathVariable("loan-id") Long loanId) {
	loanService.deleteLoanRequest(loanId);
	}
	
	@PutMapping("/acceptLoanRequest/{loan-id}")
	@ResponseBody public Loan acceptLoanRequest(@PathVariable("loan-id") Long loanId) {
		return loanService.acceptLoanRequest(loanId);
	}
	
	@PutMapping("/denyLoanRequest/{loan-id}")
	@ResponseBody public Loan denyLoanRequest(@PathVariable("loan-id") Long loanId) {
		return loanService.denyLoanRequest(loanId);
	}
	
	@GetMapping("/creditSimulation")
	  @ResponseBody
	  public String creditPaymentSimulation(@RequestParam double capital,@RequestParam int mois,@RequestParam double interest ) {
	  return loanService.simulationInterest(capital,mois,interest);
	  }
	@PostMapping("/montantRestant")
	  @ResponseBody
	  public double creditMantantRestant(@RequestParam double capital,@RequestParam int mois,@RequestParam int moisM ,@RequestParam double interest ) {
	  
	return loanService.calculMontantRestantDû(capital, mois,moisM,interest);
			
	}
	
	@PostMapping("/monthlySlice")
	  @ResponseBody
	  public double monthlyPayment(@RequestParam double capital, @RequestParam int mois,@RequestParam double interest) {
	  return loanService.calculMensualite(capital, mois, interest);
	  }

	
	@GetMapping("/getScoreAttributes/{loan-id}")
	@ResponseBody
public Map<Object, Object> getScoreAttribute(@PathVariable("loan-id") Long loanId) {
		HashMap<Object, Object> map = (HashMap<Object, Object>) loanService.getScoreAttr(loanId);
		HashMap<Object, Object> mapT = new HashMap<>();
		mapT=(HashMap<Object, Object>) loanService.scoreDataTransform(map.get("sex").toString(),
				 map.get("housing").toString(),
				Float.parseFloat(map.get("savingAccount").toString()), 
				 map.get("purpose").toString());
		
	return mapT;	
	}
	
	
	
	@GetMapping("/predict-score/{loan-id}")
	  @ResponseBody
	  public Map<Object, Object>  calculScore(@PathVariable("loan-id") Long loanId) {
		
		HashMap<Object, Object> mapA = (HashMap<Object, Object>) loanService.getScoreAttr(loanId);
		HashMap<Object, Object> mapTransf= new HashMap<>();
		HashMap<Object, Object> mapTot = new HashMap<>();
		
				//mapId.put(i, mapT);
					mapTransf=(HashMap<Object, Object>) loanService.scoreDataTransform(mapA.get("sex").toString(), 
					mapA.get("housing").toString(),
					Float.parseFloat( mapA.get("savingAccount").toString()), 
					mapA.get("purpose").toString());


					mapTot.put("age", Float.parseFloat(mapA.get("age").toString()));
					mapTot.put("sex", mapTransf.get("0"));
					mapTot.put("housing", mapTransf.get("1"));
					mapTot.put("savingAccount", mapTransf.get("2"));
					mapTot.put("purpose",  mapTransf.get("3"));
					mapTot.put("amount", mapA.get("amount"));
					mapTot.put("job", 0.0);
					mapTot.put("mois", mapA.get("mois"));
				
			
			return mapTot;
	}
	/*
	@GetMapping("/score")
	  @ResponseBody
	  public Map<Object, Object>  score() {
		
		HashMap<Object, Object> mapA = (HashMap<Object, Object>) getScoreAttributes();
		HashMap<Object, Object> mapT = new HashMap<>();
		HashMap<Object, Object> mapId = new HashMap<>();
		
			List<Loan> listLoans = loanService.retrieveAllLoans();
			for (int i = 1; i < listLoans.size(); i++) {
				//mapId.put(i, mapT);
				for (int j = 1; j < listLoans.size()+1; j++) {
					mapT=(HashMap<Object, Object>) loanService.scoreDataTransform(((HashMap<String,Object>) mapA.get(j)).get("sex").toString(), 
					((HashMap<String,Object>) mapA.get(j)).get("housing").toString(),
					Float.parseFloat(((HashMap<String,Object>) mapA.get(j)).get("savingAccount").toString()), 
					((HashMap<String,Object>) mapA.get(j)).get("purpose").toString());
					
					mapId.put(j, mapT);
					mapId.put(i+1, mapT);

					
				}
			}
			
		HashMap<String, Object> map = new HashMap<>();
		HashMap<Object, Object> mapId2 = new HashMap<>();
		for (int i = 1; i < listLoans.size(); i++) {
			for (int j = 1; j < listLoans.size()+1; j++) {
				
				map.put("age", Float.parseFloat(((HashMap<String,Object>) mapA.get(j)).get("age").toString()));
				map.put("sex", ((HashMap<String,Object>) mapId.get(j)).get("0"));
				map.put("housing", ((HashMap<String,Object>) mapId.get(j)).get("1"));
				map.put("savingAccount", ((HashMap<String,Object>) mapId.get(j)).get("2"));
				map.put("purpose", ((HashMap<String,Object>) mapId.get(j)).get("3"));
				map.put("amount", ((HashMap<String,Object>) mapA.get(j)).get("amount"));
				map.put("job", 0.0);
				map.put("mois",((HashMap<String,Object>) mapA.get(j)).get("mois"));
				mapId2.put(j, map);
				mapId2.put(i+1, map);
				map = new HashMap<>();
			}	
		}
	  return mapId2;
	  }
	*/
	
	@GetMapping("/retrieve-score")
	@ResponseBody

	public Object getScore() {
		ObjectMapper oMapper = new ObjectMapper();
		
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject("http://127.0.0.1:5000/predict", Object.class);
		 Map<String, Object> map = oMapper.convertValue(response, Map.class);
		return map;
	 
	}
	
	@GetMapping("/affectScoreToLoan")
	@ResponseBody
	public void AffectScoreToLoans() {
		loanService.affectScoreToLoans();
	}
	
	
}
