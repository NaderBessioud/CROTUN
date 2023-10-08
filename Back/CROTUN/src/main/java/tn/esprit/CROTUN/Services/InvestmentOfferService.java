package tn.esprit.CROTUN.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Repositories.InvestmentOfferRepository;

import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;

@Service
public class InvestmentOfferService implements IInvestmentOfferService {
	@Autowired
	InvestmentOfferRepository OfferRepo;
	@Autowired
	ManagerRepository ManagerRepo;
	@Autowired
	InvestorRespository InvestorRepo;
	
	
	
	public List<InvestmentOffer> retrieveAllOffers() {
		return (List<InvestmentOffer>) OfferRepo.retrieveOffer();
	}
	
	
	@Override

	public List<InvestmentOffer> retrieveAllInvestmentOffer() {
		return (List<InvestmentOffer>) OfferRepo.retrieveCurrentInvestmentOffer(false);
	}
	@Override
	public List<InvestmentOffer> retrieveAllArchivedInvestmentOffer() {
		return (List<InvestmentOffer>) OfferRepo.retrieveArchivedInvestmentOffer(true);
	}
	
	
	@Override
	public InvestmentOffer addInvestmentOffer(InvestmentOffer inv , Long id) {
		Manager m = ManagerRepo.findById(id).orElse(null);
		LocalDate localDate = LocalDate.now();
		inv.setDateCreated(localDate);	
		inv.setManagerInvOffer(m);
		OfferRepo.save(inv);
		return inv;
	}
	
	
	@Override
	public InvestmentOffer BuyInvestmentOffer(Long idIO, Long idI) {
		Investor investor = InvestorRepo.findById(idI).orElse(null);
		InvestmentOffer investmentoffer = OfferRepo.findById(idIO).orElse(null);
		LocalDate localDate = LocalDate.now();
		investmentoffer.setDateSell(localDate);
		investmentoffer.setInvestorInvest(investor);
		OfferRepo.save(investmentoffer);
		return investmentoffer;
	}

	@Override
	public void deleteInvestmentOffer(Long id) {
		OfferRepo.deleteById(id);
		
	}

	@Override
	public InvestmentOffer updateInvestmentOffer(InvestmentOffer inv) {
		OfferRepo.save(inv);
		return inv;
	}

	@Override
	public void archiveInvestmentOffer(Long id) {
		InvestmentOffer inv = OfferRepo.findById(id).orElse(null);
		inv.setStatus(true);
		OfferRepo.save(inv);
		
	}

	@Override
	public int NumberIvestmentOffer() {
		int count = OfferRepo.CountStatus(false);
		return count ;
	}
	
	@Override
	public int[] NumberSoldIvestmentOffer() {
		int countSold = OfferRepo.CountSoldStatus();
		int countUnSold = OfferRepo.CountUnSoldStatus();
		int[] count = {countSold, countUnSold};
		return count ;
	}
	
	@Override
	public int NumberUnSoldIvestmentOffer() {
		int countUnSold = OfferRepo.CountUnSoldStatus();
		
		return countUnSold ;
	}
	
	
	@Override
	public int NumberArchivedIvestmentOffer() {
		int count = OfferRepo.CountArchivedStatus(true);
		return count ;
	}

	
	@Override
	public List<InvestmentOffer> recommandation(Long id) {
		Investor inv = InvestorRepo.findById(id).orElse(null);
		 String[] tab = OfferRepo.recommandation(inv.getIdInvestor());
		 return (List<InvestmentOffer>) OfferRepo.retrieveType(tab[0]);
		 		
	}
	
	
	@Override 
	public String[] statistique() {
		String[] tab = OfferRepo.statistique();
		return tab;								
	}
	
	@Override
	public List<InvestmentOffer> retrieveInvestorOffer(Long id){			
		return (List<InvestmentOffer>) OfferRepo.retrieveInvestorOffer(id);
	}

	@Override
	public List<InvestmentOffer> retrieveManagerOffer(Long id){			
		return (List<InvestmentOffer>) OfferRepo.retrieveManagerOffer(id);
	}
	
	
	
}
