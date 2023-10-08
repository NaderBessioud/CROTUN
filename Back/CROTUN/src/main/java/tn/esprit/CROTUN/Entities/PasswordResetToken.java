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
@Table(name="PasswordToken")
public class PasswordResetToken implements Serializable{
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdPasswordToken")
private Long IdPasswordToken;
@Column(name="token")
private String token;
@Column(name="expireDate")
private Instant expireDate;

@OneToOne
private Agent agentPass;
@OneToOne
private Customer customerPass;
@OneToOne
private Manager managerPass;
@OneToOne
private Investor investorPass;


public PasswordResetToken() {
	
}
public Long getIdPasswordToken() {
	return IdPasswordToken;
}
public void setIdPasswordToken(Long idPasswordToken) {
	IdPasswordToken = idPasswordToken;
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
public Agent getAgentPass() {
	return agentPass;
}
public void setAgentPass(Agent agentPass) {
	this.agentPass = agentPass;
}
public Customer getCustomerPass() {
	return customerPass;
}
public void setCustomerPass(Customer customerPass) {
	this.customerPass = customerPass;
}
public Manager getManagerPass() {
	return managerPass;
}
public void setManagerPass(Manager managerPass) {
	this.managerPass = managerPass;
}
public Investor getInvestorPass() {
	return investorPass;
}
public void setInvestorPass(Investor investorPass) {
	this.investorPass = investorPass;
}
@Override
public String toString() {
	return "PasswordResetToken [IdPasswordToken=" + IdPasswordToken + ", token=" + token + ", expireDate=" + expireDate
			+ ", agentPass=" + agentPass + ", customerPass=" + customerPass + ", managerPass=" + managerPass
			+ ", investorPass=" + investorPass + "]";
}



}
