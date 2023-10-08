package tn.esprit.CROTUN.Services;

import java.util.List;

import tn.esprit.CROTUN.Entities.DetailLoan;

public interface IDetailLoanService {
	public DetailLoan addDetailLoan(DetailLoan s,Long IdL);
		
	public double calculMensualite(double capital, int mois,double interest);
	
	public List<DetailLoan> listAll();
}
