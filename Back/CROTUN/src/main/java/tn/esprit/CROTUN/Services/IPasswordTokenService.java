package tn.esprit.CROTUN.Services;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Exception.UserNotFoundException;

public interface IPasswordTokenService {
	
	PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException;
	boolean VerifyExpiration(String token);
	void ConfirmPasswordReset(String token,String password);
	
	List<PasswordResetToken> getExpireToken();
	
	void deleteToken(Long id);
	

}
