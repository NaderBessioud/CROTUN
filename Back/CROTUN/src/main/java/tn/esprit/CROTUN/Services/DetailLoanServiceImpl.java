package tn.esprit.CROTUN.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.DetailLoan;
import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Repositories.DetailLoanRepository;
import tn.esprit.CROTUN.Repositories.LoanRepository;

@Service
public class DetailLoanServiceImpl implements IDetailLoanService {
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	DetailLoanRepository detailLoanRepository;
	
	
	@Override
	public DetailLoan addDetailLoan(DetailLoan s, Long IdL) {
		s.setLoansDetail(loanRepository.findById(IdL).orElse(null));
		
		Loan l=loanRepository.findById(IdL).get();
		
		double MontantRestant;
		double interestM = l.getTotalAmount()*(l.getInterest()/12);
		double amortissement = calculMensualite(l.getTotalAmount(), l.getLoanPeriodInMonths(),l.getInterest()) - interestM;
		MontantRestant=l.getTotalAmount();
		
		for (int i=0;i<l.getLoanPeriodInMonths();i++) {
			
			DetailLoan sl  = new DetailLoan ();
			sl.setLoansDetail(loanRepository.findById(IdL).orElse(null));
			sl.setMensualite(calculMensualite(l.getTotalAmount(), l.getLoanPeriodInMonths(),l.getInterest()));
			
			sl.setMontantRestant(MontantRestant);
			sl.setAmortissements(amortissement);
			sl.setInterest(interestM);
			
			MontantRestant = MontantRestant - amortissement;
			interestM = (l.getInterest()/12) * MontantRestant;
			amortissement = calculMensualite(l.getTotalAmount(), l.getLoanPeriodInMonths(),l.getInterest()) - interestM;

			
			detailLoanRepository.save(sl);
			
		}
		return s;
	}
	
	@Override
	public double calculMensualite(double capital, int mois,double interest) {
		
		double mens=0;
		mens=(capital*(interest/12))/
				(1-Math.pow((1+interest/12),-mois));
		return mens;
	}
	
    public List<DetailLoan> listAll() {
        return (List<DetailLoan>) detailLoanRepository.findAll();
    }


}
