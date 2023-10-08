package tn.esprit.CROTUN.Services;

import java.util.List;
import java.util.Map;

import tn.esprit.CROTUN.Entities.Housing;
import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.LoanPurpose;

public interface ILoanService {
	Loan addLoanRequest(Loan l,Long IdC);
	
	Loan acceptLoanRequest(Long id);
	
	Loan denyLoanRequest(Long id);
	
	public Map<Object, Object> getScoreAttr(Long loanId);
	
	void deleteLoanRequest(long id);

	List<Loan> retrieveAllLoans();

	Loan retrieveLoan(long id);
	
	public double predictScore(long id);
	
	
	public void affectScoreToLoans();
	//public  Map<Object, Object> getScoreAttributes();
	
	
	public double calculMensualite(double capital, int mois, double interest);
	
	
	public double calculMontantRestantDû(double capital, int mois,int moisM, double interest);
	
	
	public double calculMantantInterest(double capital, double montantTotal);
	
	public String simulationInterest(double capital, int montant, double interest);
	
	public Map<Object, Object> scoreDataTransform(String sex, String housing, float savingAccount,String purpose);
	
}
