package tn.esprit.CROTUN.Services;

import java.time.LocalDate;
import java.util.List;

import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.InvestmentOffer;

public interface IInvestmentService {
	 Investment addInvestment(Investment inv,Long id);
	 String simulationInvestment(LocalDate start,LocalDate end, float amount);
	 List<Investment> retrieveAllInvestment();
	 List<Investment> retrieveInvestorInvestment(Long id);
	 void archiveInvestment();
	 float CountAllInvestment();
	float CountProfitInvestment();
	float CountTotalInvestment();



}
