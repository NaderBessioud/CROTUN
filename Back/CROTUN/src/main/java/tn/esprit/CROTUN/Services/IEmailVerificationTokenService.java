package tn.esprit.CROTUN.Services;

import java.util.Date;
import java.util.List;

import tn.esprit.CROTUN.Entities.EmailVerificationToken;

public interface IEmailVerificationTokenService {
	EmailVerificationToken createEmailVerificationToken(Object user);
	
	void ConfirmUserRegistration(String Token);
	
	List<EmailVerificationToken> getExpireToken();
	
	void deleteToken(Long id);

}
