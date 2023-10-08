package tn.esprit.CROTUN.Advice;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import tn.esprit.CROTUN.Entities.ErrorMessageResponse;
import tn.esprit.CROTUN.Exception.TokenEmailException;
import tn.esprit.CROTUN.Exception.TokenRefreshException;
import tn.esprit.CROTUN.Exception.UserNotFoundException;

@RestControllerAdvice
public class AuthControllerAdvice {
	@ExceptionHandler(value=TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessageResponse handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		return new ErrorMessageResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage(), Instant.now(),ex.getCause().getMessage(),request.getDescription(false));
	  }
	
	@ExceptionHandler(value=TokenEmailException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessageResponse handleTokenEmailException(TokenEmailException ex,WebRequest request) {
		return new ErrorMessageResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(), Instant.now(),ex.getCause().getMessage(),request.getDescription(false));
	}
	
	@ExceptionHandler(value=UserNotFoundException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessageResponse handleUserNotFoundException(UserNotFoundException ex,WebRequest request) {
		return new ErrorMessageResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(), Instant.now(),ex.getCause().getMessage(),request.getDescription(false));
	}
	
}
