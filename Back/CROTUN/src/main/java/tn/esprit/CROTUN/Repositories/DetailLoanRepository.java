package tn.esprit.CROTUN.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.DetailLoan;
@Repository
public interface DetailLoanRepository extends CrudRepository <DetailLoan,Long> {

}
