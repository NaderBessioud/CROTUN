package tn.esprit.CROTUN.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Loan;

@Repository
public interface LoanRepository extends CrudRepository <Loan,Long> {

}
