package tn.esprit.CROTUN.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.CROTUN.Entities.Penalty;

@Repository
public interface PenaltyRepository extends CrudRepository <Penalty,Long> {

}
