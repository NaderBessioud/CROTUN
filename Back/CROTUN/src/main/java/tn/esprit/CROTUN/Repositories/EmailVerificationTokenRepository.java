package tn.esprit.CROTUN.Repositories;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.EmailVerificationToken;

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {

	EmailVerificationToken findByToken(String token);
	
	@Query("Select T From EmailVerificationToken T where T.expireDate < ?1")
	List<EmailVerificationToken> findExpireToken(Instant date);
	

}
