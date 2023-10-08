package tn.esprit.CROTUN.Services;

import java.util.Optional;

import tn.esprit.CROTUN.Entities.RefreshToken;
import tn.esprit.CROTUN.security.UserPrincipal;

public interface IRefreshTokenService {
	
	Optional<RefreshToken> getByToken(String token);
	RefreshToken CreateRefreshToken(UserPrincipal principal);
	boolean VerifyExpiration(RefreshToken token);
	int DeleteByUser(String username);

}
