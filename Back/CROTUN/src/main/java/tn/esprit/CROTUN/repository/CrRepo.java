package tn.esprit.CROTUN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Customer;
@Repository
public interface CrRepo extends JpaRepository<Customer, Long>{

}
