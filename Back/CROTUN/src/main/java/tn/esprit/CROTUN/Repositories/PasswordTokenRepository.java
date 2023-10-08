package tn.esprit.CROTUN.Repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.EmailVerificationToken;
import tn.esprit.CROTUN.Entities.PasswordResetToken;

@Repository
public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);
	
	@Query("Select T From PasswordResetToken T where T.expireDate < ?1")
	List<PasswordResetToken> findExpireToken(Instant date);

}
