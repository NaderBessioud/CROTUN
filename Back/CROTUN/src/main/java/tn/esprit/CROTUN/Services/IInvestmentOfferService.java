package tn.esprit.CROTUN.Services;
import java.util.List;
import tn.esprit.CROTUN.Entities.InvestmentOffer;


public interface IInvestmentOfferService  {
	 List<InvestmentOffer> retrieveAllInvestmentOffer();
	 List<InvestmentOffer> retrieveAllArchivedInvestmentOffer();
	void deleteInvestmentOffer(Long id);
	InvestmentOffer updateInvestmentOffer (InvestmentOffer inv);
	void archiveInvestmentOffer(Long id);
	int NumberIvestmentOffer();
	int NumberArchivedIvestmentOffer();
	InvestmentOffer addInvestmentOffer(InvestmentOffer inv, Long id);
	InvestmentOffer BuyInvestmentOffer(Long idIO, Long idI);
	List<InvestmentOffer> recommandation(Long id);
	String[] statistique();
	List<InvestmentOffer> retrieveInvestorOffer(Long id);
	List<InvestmentOffer> retrieveManagerOffer(Long id);
	List<InvestmentOffer> retrieveAllOffers();
	int[] NumberSoldIvestmentOffer();
	int NumberUnSoldIvestmentOffer();
	

}
