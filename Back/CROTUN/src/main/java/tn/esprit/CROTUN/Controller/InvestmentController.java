package tn.esprit.CROTUN.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Services.IInvestmentOfferService;
import tn.esprit.CROTUN.Services.IInvestmentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/investment")
public class InvestmentController {
	@Autowired 
	IInvestmentService iInvestmentService;
	
	@PostMapping("/addInvestment")
	@ResponseBody
	public Investment addInvestment(@RequestBody Investment inv,@RequestParam("id") Long id ) {
		try {
			iInvestmentService.addInvestment(inv,id);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return inv;
	}
	
	@PostMapping("/simulation")
	public String simulationInvestment(@RequestParam("start") String start,@RequestParam("end") String end,@RequestParam("amount") int amount) {

		try {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  

			  //convert String to LocalDate
			  LocalDate start1 = LocalDate.parse(start, formatter);
			  LocalDate end1 = LocalDate.parse(end, formatter);
			
			return iInvestmentService.simulationInvestment(start1, end1, amount);
		}catch(Exception e) {
			System.out.print(e.getMessage());
			return null;
		
		
	}
	}
	
	@GetMapping("/retrieveAllInvestment")
	@ResponseBody
	public List<Investment> getInvestment() {
	List<Investment> listInvestment = iInvestmentService.retrieveAllInvestment();
	return listInvestment;
	}
	
	@GetMapping("/retrieveInvestorInvestment")
	@ResponseBody
	public List<Investment> getInvestorInvestment(@RequestParam("idC") Long idC) {
	List<Investment> list = iInvestmentService.retrieveInvestorInvestment(idC);
	return list;
	}
	
 // archiveInvestment schedule
	
	@GetMapping("/CountAllInvestment")
	@ResponseBody
	public float CountAllInvestment() {
		float count = iInvestmentService.CountAllInvestment();
	return count;
	}
	
	@GetMapping("/CountProfitInvestment")
	@ResponseBody
	public float CountProfitInvestment() {
		float count = iInvestmentService.CountProfitInvestment();
	return count;
	}
	
	@GetMapping("/CountTotalInvestment")
	@ResponseBody
	public float CountTotalInvestment() {
		float count = iInvestmentService.CountTotalInvestment();
	return count;
	}

}
