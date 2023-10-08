package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.InvestmentOffer;


@Repository
public interface InvestmentOfferRepository extends JpaRepository<InvestmentOffer, Long> {

	@Query("SELECT i FROM InvestmentOffer i WHERE i.status= :status AND dateSell IS NULL")
	List<InvestmentOffer>retrieveCurrentInvestmentOffer(
			@Param("status") Boolean status);
	
	@Query("SELECT i FROM InvestmentOffer i WHERE i.status= :status ")
	List<InvestmentOffer>retrieveArchivedInvestmentOffer(
			@Param("status") Boolean status);
	

	@Query("SELECT count(i) FROM InvestmentOffer i WHERE status= 0 AND i.status=:status")
	 int CountStatus(@Param("status") boolean status);
	
	@Query("SELECT count(i) FROM InvestmentOffer i WHERE status= 0 AND i.dateSell!=NULL")
	 int CountSoldStatus();
	@Query("SELECT count(i) FROM InvestmentOffer i WHERE status= 0 AND i.dateSell=NULL")
	 int CountUnSoldStatus();
	@Query("SELECT count(i) FROM InvestmentOffer i WHERE i.status=:status")
	 int CountArchivedStatus(@Param("status") boolean status);

	@Query(value = "SELECT type FROM investment_offer WHERE status=0 AND investor_invest_id_investor=:investor_invest_id_investor GROUP BY type ORDER BY COUNT(type) DESC" , nativeQuery = true)
			String[] recommandation(@Param("investor_invest_id_investor") Long investor_invest_id_investor);

	@Query("SELECT i FROM InvestmentOffer i WHERE i.type=:type AND date_sell IS NULL AND status=0 ")
	List<InvestmentOffer> retrieveType(@Param("type") String type);

	
	@Query(value = "SELECT date_sell, COUNT(date_sell)  FROM investment_offer WHERE date_sell IS NOT NUll GROUP BY date_sell" , nativeQuery = true)
	String[] statistique();
	
	
	@Query(value = "SELECT * FROM investment_offer WHERE status= 0 AND investor_invest_id_investor=:investor_invest_id_investor " , nativeQuery = true)
	List<InvestmentOffer> retrieveInvestorOffer(@Param("investor_invest_id_investor") Long investor_invest_id_investor);
	
	@Query(value = "SELECT * FROM investment_offer WHERE status= 0 AND manager_inv_offer_idm=:manager_inv_offer_idm " , nativeQuery = true)
	List<InvestmentOffer> retrieveManagerOffer(@Param("manager_inv_offer_idm") Long manager_inv_offer_idm);

	@Query(value = "SELECT * FROM investment_offer WHERE status= 0 " , nativeQuery = true)
	List<InvestmentOffer> retrieveOffer();



	


}



