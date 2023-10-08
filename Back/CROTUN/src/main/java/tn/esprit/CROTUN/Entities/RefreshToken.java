package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RefreshToken")
public class RefreshToken  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdToken")
	private Long idToken;
	@Column(name="token",nullable = false,unique = true)
	private String token;
	@Column(name="expiryDate",nullable = false)
	private Instant expireDate;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Agent agentToken;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customerToken;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Manager managerToken;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Investor investorToken;



	public RefreshToken(Long idToken, String token, Instant expireDate, Agent agentToken) {
		
		this.idToken = idToken;
		this.token = token;
		this.expireDate = expireDate;
	
		this.agentToken = agentToken;
	}
	
	

	public RefreshToken(Long idToken, String token, Instant expireDate,  Customer customerToken) {
		
		this.idToken = idToken;
		this.token = token;
		this.expireDate = expireDate;
		this.customerToken = customerToken;
	}
	
	



	public RefreshToken(Long idToken, String token, Instant expireDate, Manager managerToken) {
		
		this.idToken = idToken;
		this.token = token;
		this.expireDate = expireDate;
		
		this.managerToken = managerToken;
	}

	
	


	public RefreshToken() {
		
	}



	public Long getIdToken() {
		return idToken;
	}

	public void setIdToken(Long idToken) {
		this.idToken = idToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Instant expireDate) {
		this.expireDate = expireDate;
	}

	public Agent getAgentToken() {
		return agentToken;
	}

	public void setAgentToken(Agent agentToken) {
		this.agentToken = agentToken;
	}

	public Customer getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(Customer customerToken) {
		this.customerToken = customerToken;
	}

	public Manager getManagerToken() {
		return managerToken;
	}

	public void setManagerToken(Manager managerToken) {
		this.managerToken = managerToken;
	}



	public Investor getInvestorToken() {
		return investorToken;
	}



	public void setInvestorToken(Investor investorToken) {
		this.investorToken = investorToken;
	}
	
	

}
