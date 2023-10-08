package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.time.Instant;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emailToken")
public class EmailVerificationToken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long IdEmailToken;
	@Column(name="token")
	private String token;
	@Column(name="expireDate")
	private Instant expireDate;
	
	@OneToOne
	private Agent agentEmail;
	@OneToOne
	private Customer customerEmail;
	@OneToOne
	private Manager managerEmail;
	
	@OneToOne
	private Investor investorEmail;
	public EmailVerificationToken() {
	
	}
	public EmailVerificationToken(Long idEmailToken, String token, Instant expireDate, Agent agentEmail,
			Customer customerEmail, Manager managerEmail) {
		super();
		IdEmailToken = idEmailToken;
		this.token = token;
		this.expireDate = expireDate;
		this.agentEmail = agentEmail;
		this.customerEmail = customerEmail;
		this.managerEmail = managerEmail;
	}
	public EmailVerificationToken(Long idEmailToken, String token, Instant expireDate, Agent agentEmail) {
		super();
		IdEmailToken = idEmailToken;
		this.token = token;
		this.expireDate = expireDate;
		this.agentEmail = agentEmail;
	}
	public EmailVerificationToken(Long idEmailToken, String token, Instant expireDate, Customer customerEmail) {
		super();
		IdEmailToken = idEmailToken;
		this.token = token;
		this.expireDate = expireDate;
		this.customerEmail = customerEmail;
	}
	public EmailVerificationToken(Long idEmailToken, String token, Instant expireDate, Manager managerEmail) {
		super();
		IdEmailToken = idEmailToken;
		this.token = token;
		this.expireDate = expireDate;
		this.managerEmail = managerEmail;
	}
	public Long getIdEmailToken() {
		return IdEmailToken;
	}
	public void setIdEmailToken(Long idEmailToken) {
		IdEmailToken = idEmailToken;
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
	public Agent getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(Agent agentEmail) {
		this.agentEmail = agentEmail;
	}
	public Customer getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(Customer customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Manager getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(Manager managerEmail) {
		this.managerEmail = managerEmail;
	}
	public Investor getInvestorEmail() {
		return investorEmail;
	}
	public void setInvestorEmail(Investor investorEmail) {
		this.investorEmail = investorEmail;
	}
	
	
	
	

}
