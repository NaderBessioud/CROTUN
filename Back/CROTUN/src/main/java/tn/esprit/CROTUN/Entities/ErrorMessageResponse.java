package tn.esprit.CROTUN.Entities;

import java.time.Instant;


public class ErrorMessageResponse {
	
	private final int code;
	private final String message;

    private final Instant date;
    private final String cause;
    private final String path;
	
	
	
	public ErrorMessageResponse(int code, String message, Instant date, String cause, String path) {
		super();
		this.code = code;
		this.message = message;
		
		this.date = date;
		this.cause = cause;
		this.path = path;
	}
	
	
	
public ErrorMessageResponse(String message, Instant date, String cause, String path) {
		
		this.message = message;
		
		this.date = date;
		this.cause = cause;
		this.path = path;
		this.code=0;
	}





	public ErrorMessageResponse(int code, String message, Instant date, String path) {
	
	this.code = code;
	this.message = message;
	this.date = date;
	this.path = path;
	this.cause=null;
}



	public String getMessage() {
		return message;
	}
	
	public Instant getDate() {
		return date;
	}
	public String getCause() {
		return cause;
	}
	public String getPath() {
		return path;
	}
    
    

}
