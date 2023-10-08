package tn.esprit.CROTUN.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.CustomerPenality;
import tn.esprit.CROTUN.Entities.CustomerPenalityDate;
import tn.esprit.CROTUN.Entities.CustomerPenality;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByUserNameAndPassword(String username,String password);
	Customer findByEmail(String email);
	Customer findByUserName(String username);
	
	
	  @Query("SELECT " +
	           "    new tn.esprit.CROTUN.Entities.CustomerPenality(c.IdC,c.firstName, c.lastName, SUM(p.Amount)) " +
	           "FROM " +
	           "    Customer c join  c.LoansCust l join l.slices s join s.penSlice p  where l.Archive=false "+
	           "GROUP BY " +
	           " c.IdC" )
	public List<CustomerPenality> getPenalityByCustomer();
	  
	  @Query("SELECT " +
	           "    new tn.esprit.CROTUN.Entities.CustomerPenalityDate(c.IdC,c.firstName, c.lastName,p.Amount,p.RepaymentDate) " +
	           "FROM " +
	           "    Customer c join  c.LoansCust l join l.slices s join s.penSlice p  where l.Archive=false and c.IdC=?1 "+
	           
	           "GROUP BY " +
	           " c.IdC" 
	           )
	public List<CustomerPenalityDate> getPenalityByCustomerByDate(Long id);
	  
	  
	  
	  
	  List<Customer> findByBanned(Boolean isBanned);
	  List<Customer> findByEnabled(Boolean isEnabled);
}
