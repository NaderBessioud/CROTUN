package tn.esprit.CROTUN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Payement;

@Repository
public interface PayementRepository extends JpaRepository<Payement, Long>{

}
