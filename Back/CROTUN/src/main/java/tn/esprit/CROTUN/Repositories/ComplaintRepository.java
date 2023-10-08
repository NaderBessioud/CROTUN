package tn.esprit.CROTUN.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.Complaint;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long>{
	@Query("SELECT count(i) FROM Complaint i WHERE i.status=:status")
	int CountStatus(@Param("status") Boolean status);
	@Query("SELECT count(i) FROM Complaint i ")
	int CountAllStatus();
}
