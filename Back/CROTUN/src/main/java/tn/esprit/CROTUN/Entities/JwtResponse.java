package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

public class JwtResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final String JWTToken;
	private final String RefreshToken;
	
	private Agent agent;
	private Customer customer;
	private Manager manager;
	private Investor investor;
	
	
	public JwtResponse(String jWTToken, String refreshToken) {
		super();
		JWTToken = jWTToken;
		RefreshToken = refreshToken;
	}
	
	public JwtResponse(String jWTToken, String refreshToken, Agent agent) {
		super();
		JWTToken = jWTToken;
		RefreshToken = refreshToken;
		this.agent = agent;
	}

	public JwtResponse(String jWTToken, String refreshToken, Investor investor) {
		super();
		JWTToken = jWTToken;
		RefreshToken = refreshToken;
		this.investor = investor;
	}

	public JwtResponse(String jWTToken, String refreshToken, Manager manager) {
		super();
		JWTToken = jWTToken;
		RefreshToken = refreshToken;
		this.manager = manager;
	}

	public JwtResponse(String jWTToken, String refreshToken, Customer customer) {
		super();
		JWTToken = jWTToken;
		RefreshToken = refreshToken;
		this.customer = customer;
	}

	public String getJWTToken() {
		return JWTToken;
	}





	public String getRefreshToken() {
		return RefreshToken;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
	
	
	
	
	

}
