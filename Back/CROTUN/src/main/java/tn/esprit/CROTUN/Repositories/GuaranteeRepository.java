package tn.esprit.CROTUN.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Guarantee;

@Repository
public interface GuaranteeRepository extends CrudRepository<Guarantee, Long> {

}
