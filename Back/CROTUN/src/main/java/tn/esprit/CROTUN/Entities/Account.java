package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdAC")
private long IdAC;
@Column(name="Amount")
private int Amount;
@Column(name="RIB")
private String RIB;

@Column(name="CreatedAt")
private Date CreatedAt;
@JsonIgnore
@ManyToOne
private Agent agentAccount;
@JsonIgnore
@OneToOne(mappedBy = "accountCustomer")
private Customer customerAccount;
@JsonIgnore
@OneToMany(mappedBy = "transactionAccount")
private Set<Transaction> accountTransaction;

public long getIdAC() {
	return IdAC;
}

public void setIdAC(long idAC) {
	IdAC = idAC;
}

public int getAmount() {
	return Amount;
}

public void setAmount(int amount) {
	Amount = amount;
}

public String getRIB() {
	return RIB;
}

public void setRIB(String rIB) {
	RIB = rIB;
}

public Date getCreatedAt() {
	return CreatedAt;
}

public void setCreatedAt(Date createdAt) {
	CreatedAt = createdAt;
}

public Agent getAgentAccount() {
	return agentAccount;
}

public void setAgentAccount(Agent agentAccount) {
	this.agentAccount = agentAccount;
}

public Customer getCustomerAccount() {
	return customerAccount;
}

public void setCustomerAccount(Customer customerAccount) {
	this.customerAccount = customerAccount;
}

public Set<Transaction> getAccountTransaction() {
	return accountTransaction;
}

public void setAccountTransaction(Set<Transaction> accountTransaction) {
	this.accountTransaction = accountTransaction;
}


}
