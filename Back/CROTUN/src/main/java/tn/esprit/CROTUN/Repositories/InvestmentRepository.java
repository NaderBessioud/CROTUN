package tn.esprit.CROTUN.Repositories;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
	
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Investment;

@Repository
public interface InvestmentRepository extends CrudRepository<Investment, Long> {
	List<Investment> findTop3ByOrderByAmountDesc();
	
	@Query(value="SELECT * FROM investment WHERE status= 0 " , nativeQuery = true)
	List<Investment>retrieveAllInvestmentOffer();
	
	@Query(value="SELECT SUM(amount) FROM investment WHERE status= 0 " , nativeQuery = true)
	float CountAllInvestment();
	@Query(value="SELECT SUM(profit) FROM investment WHERE status= 0 " , nativeQuery = true)
	float CountProfitInvestment();
	@Query(value="SELECT SUM(total_amount) FROM investment WHERE status= 0 " , nativeQuery = true)
	float CountTotalInvestment();
	@Query(value = "SELECT * FROM investment WHERE investors_id_investor=:investors_id_investor AND status= 0" , nativeQuery = true)
	List<Investment> retrieveInvestorInvestment(@Param("investors_id_investor") Long investors_id_investor);

	@Transactional
	@Query(value = "UPDATE investment SET status = 1 WHERE DATEDIFF(date_end,NOW())<1" , nativeQuery = true)
	void ArchiveInvestment();

}
