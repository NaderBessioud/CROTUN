package tn.esprit.CROTUN.Services;


import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Repositories.InvestmentOfferRepository;
import tn.esprit.CROTUN.Repositories.InvestmentRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;

@Service
public class InvestmentService implements IInvestmentService {

	@Autowired
	InvestmentRepository InvestRepo;
	@Autowired
	InvestorRespository InvestorRepo;

	@Override
	public Investment addInvestment(Investment inv,Long id) {
		Investor investor = InvestorRepo.findById(id).orElse(null);
		inv.setInvestors(investor);
		LocalDate localDate = LocalDate.now();
		inv.setDateStart(localDate);
		InvestRepo.save(inv);
		int days =   (int) ChronoUnit.DAYS.between(inv.getDateStart(), inv.getDateEnd());
		inv.setDuration(days);
		inv.setRate((float) (((0.15*days)/365)*100));
		inv.setProfit((inv.getAmount()*inv.getRate())/100 );
		inv.setTotalAmount(inv.getAmount()+inv.getProfit());
		InvestRepo.save(inv);
		return inv;
	}
	
	public String simulationInvestment(LocalDate start,LocalDate end, float amount) {
		int days =   (int) ChronoUnit.DAYS.between(start, end);
		float rate = (float) (((0.15*days)/365)*100);
		float profit = (amount*rate)/100;
		float TotalAmount = amount + profit;
		//return("Votre amount est : " + amount + "DNT | Le taux d'investissement : " +rate+" % | Gain d'argent : "+profit+"DNT | Mountant total:" +TotalAmount+" DNT."); 
		return("Total amount : "+TotalAmount+" DNT Profit : "+profit+" DNT  ___Rate : "+rate+" %");
		
	}
	
	
	@Override
	public List<Investment> retrieveAllInvestment() {
		return (List<Investment>) InvestRepo.retrieveAllInvestmentOffer();
	}
	
	
	
	@Override
	public List<Investment> retrieveInvestorInvestment(Long id){			
		return (List<Investment>) InvestRepo.retrieveInvestorInvestment(id);
	}
	
	
	@Scheduled(fixedDelay = 600000)
	@Override
	public void archiveInvestment() {
		LocalDate localDate = LocalDate.now();
		for (Investment inv : InvestRepo.findAll()) {
			LocalDate end = inv.getDateEnd();
			if(localDate.isAfter(end) == true) {
				inv.setStatus(true);
				InvestRepo.save(inv);
				System.out.println("L'investissement "+inv.getIdIN()+" est terminé ");
			}
		}
	}


	@Override
	public float CountAllInvestment() {
		float count = InvestRepo.CountAllInvestment();
		return count ;
	}
	
	@Override
	public float CountProfitInvestment() {
		float count = InvestRepo.CountProfitInvestment();
		return count ;
	}
	
	@Override
	public float CountTotalInvestment() {
		float count = InvestRepo.CountTotalInvestment();
		return count ;
	}

}
