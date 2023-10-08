package tn.esprit.CROTUN.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Services.IInvestmentOfferService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/investmentoffer")
public class InvestmentOfferController {
	@Autowired 
	IInvestmentOfferService iInvestmentOfferService;
	


	@PostMapping("/addInvestmentOffer")
	@ResponseBody
	public InvestmentOffer addOffer(@RequestBody InvestmentOffer inv, @RequestParam("id") Long id) {
		try {
			iInvestmentOfferService.addInvestmentOffer(inv,id);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return inv;
	}
	
	@PutMapping("/buyInvestmentOffer")
	@ResponseBody
	public void buyOffer(@RequestParam("idIO") Long idIO, @RequestParam("idI") Long idI) {
		try {
			iInvestmentOfferService.BuyInvestmentOffer(idIO, idI);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/updateInvestmentOffer")
	@ResponseBody
	public InvestmentOffer updateInvestmentOffer(@RequestBody InvestmentOffer inv) {
		try {
			iInvestmentOfferService.updateInvestmentOffer(inv);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return inv;
	}
	

	@GetMapping("/retrieveAllInvestmentOffer")
	@ResponseBody
	public List<InvestmentOffer> getInvestmentOffer() {
	List<InvestmentOffer> listInvestmentOffer = iInvestmentOfferService.retrieveAllInvestmentOffer();
	return listInvestmentOffer;
	}
	
	@GetMapping("/retrieveAllOffers")
	@ResponseBody
	public List<InvestmentOffer> getOffers() {
	List<InvestmentOffer> listInvestmentOffer = iInvestmentOfferService.retrieveAllOffers();
	return listInvestmentOffer;
	}
	
	
	@GetMapping("/retrieveAllArchivedInvestmentOffer")
	@ResponseBody
	public List<InvestmentOffer> getArchivedInvestmentOffer() {
	List<InvestmentOffer> listInvestmentOffer = iInvestmentOfferService.retrieveAllArchivedInvestmentOffer();
	return listInvestmentOffer;
	}
	
	@GetMapping("/retrieveInvestorInvestmentOffer")
	@ResponseBody
	public List<InvestmentOffer> getInvestorInvestmentOffer(@RequestParam("id") Long id) {
	List<InvestmentOffer> listInvestmentOffer = iInvestmentOfferService.retrieveInvestorOffer(id);
	return listInvestmentOffer;
	}
	
	@GetMapping("/retrieveManagerInvestmentOffer")
	@ResponseBody
	public List<InvestmentOffer> getManagerInvestmentOffer(@RequestParam("id") Long id) {
	List<InvestmentOffer> listInvestmentOffer = iInvestmentOfferService.retrieveManagerOffer(id);
	return listInvestmentOffer;
	}
	
	
	
	@GetMapping("/NumberInvestmentOffer")
	@ResponseBody
	public int getCountInvestmentOffer() {
	int count = iInvestmentOfferService.NumberIvestmentOffer();
	return count;
	}
	
	@GetMapping("/NumberSoldInvestmentOffer")
	@ResponseBody
	public int[] getCountSoldInvestmentOffer() {
	int[] count = iInvestmentOfferService.NumberSoldIvestmentOffer();
	return count;
	}
	@GetMapping("/NumberUnSoldInvestmentOffer")
	@ResponseBody
	public int getCountUnSoldInvestmentOffer() {
	int count = iInvestmentOfferService.NumberUnSoldIvestmentOffer();
	return count;
	}
	@GetMapping("/NumberArchivedInvestmentOffer")
	@ResponseBody
	public int getCountArchivedInvestmentOffer() {
	int count = iInvestmentOfferService.NumberArchivedIvestmentOffer();
	return count;
	}

	
	@GetMapping("/statistique")
	@ResponseBody
	public String[] statistiqueInvestmentOffer() {
	String[] tab = iInvestmentOfferService.statistique();
	return tab;
	}
	
	@GetMapping("/recommandation")
	@ResponseBody
	public List<InvestmentOffer> recommandationInvestmentOffer(@RequestParam("id") Long id) {
		List<InvestmentOffer> tab = iInvestmentOfferService.recommandation(id);
	return tab;
	}
	
	

	@PutMapping("/archiverInvestmentOffer")
	@ResponseBody
	public void archiveInvestmentOffer(@RequestParam("id") Long id) {
		try {
			iInvestmentOfferService.archiveInvestmentOffer(id);
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
	}
	


	

	
	

}
