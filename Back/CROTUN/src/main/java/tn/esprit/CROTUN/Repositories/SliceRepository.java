package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Slice;


@Repository
public interface SliceRepository extends CrudRepository<Slice, Long> {
	@Query(value = "SELECT loan_slice_idl  FROM Slice f WHERE  DATEDIFF (f.Repayment_Date,NOW())=2" , nativeQuery = true)
			List<Long> retrievenotifloans();
	
	@Query(value = "SELECT loan_slice_idl  FROM Slice f WHERE  (f.Verified=1) AND ((SELECT MONTH( f.Repayment_Date ))=MONTH(Now()))  " , nativeQuery = true)
	List<Long> retrievepayedmonthloans();
	
	@Query(value = "SELECT *  FROM Slice f WHERE  ((f.loan_slice_idl=:idloan) AND (f.Verified=0))  " , nativeQuery = true)
	List<Slice> retrieveremainingslices(@Param("idloan") Long idloan);

}
