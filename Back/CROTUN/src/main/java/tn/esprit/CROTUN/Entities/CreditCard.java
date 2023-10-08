package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="CreditCard")
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdCCard")
private long IdCCard;
@Column(name="Code")
private String Code;
@Column(name="Amount")
private float Amount;
@Column(name="State")

private Boolean State=true;
@Column(name="qrcredit")
private String Qrcredit;



public CreditCard() {
	super();
}



public CreditCard(long idCCard, String code, long amount, Boolean state, String qrcredit, Agent agentCard,
		Customer customerCard) {
	super();
	IdCCard = idCCard;
	Code = code;
	Amount = amount;
	State = state;
	Qrcredit = qrcredit;
	this.agentCard = agentCard;
	this.customerCard = customerCard;
}



public String getQrcredit() {
	return Qrcredit;
}



public void setQrcredit(String qrcredit) {
	Qrcredit = qrcredit;
}



public long getIdCCard() {
	return IdCCard;
}

public void setIdCCard(long idCCard) {
	IdCCard = idCCard;
}

public String getCode() {
	return Code;
}

public void setCode(String code2) {
	Code = code2;
}

public float getAmount() {
	return Amount;
}

public void setAmount(long amount) {
	Amount = amount;
}

public Boolean getState() {
	return State;
}

public void setState(Boolean state) {
	State = state;
}

public Agent getAgentCard() {
	return agentCard;
}

public void setAgentCard(Agent agentCard) {
	this.agentCard = agentCard;
}

public Customer getCustomerCard() {
	return customerCard;
}

public void setCustomerCard(Customer customerCard) {
	this.customerCard = customerCard;
}

@ManyToOne
private Agent agentCard;

@ManyToOne
private Customer customerCard;
}
