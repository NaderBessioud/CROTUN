package tn.esprit.CROTUN.messagenotifapi;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Twilioproperties {
	private String accountSid = "AC3438c0ee24d0908117f132bb485c28f9";
	private String authToken = "3a31cbb8fcda278e1def5a52af8b972a";
	private String fromNumber = "+17343751603";
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	public Twilioproperties() {
		super();
		// TODO Auto-generated constructor stub
	}

}
