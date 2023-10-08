package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Payement")
public class Payement implements Serializable{
	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdPay")
private long IdPay;
@Column(name="FristName")
private String FristName;
@Column(name="LastName")
private String LastName;
@Column(name="Amount")
private int Amount;
public Payement() {
	super();
}


public Payement(long idPay, String fristName, String lastName, int amount, String rIB, Slice slicePayment) {
	super();
	IdPay = idPay;
	FristName = fristName;
	LastName = lastName;
	Amount = amount;
	RIB = rIB;
	this.slicePayment = slicePayment;
}


public long getIdPay() {
	return IdPay;
}


public void setIdPay(long idPay) {
	IdPay = idPay;
}


public String getFristName() {
	return FristName;
}


public void setFristName(String fristName) {
	FristName = fristName;
}


public String getLastName() {
	return LastName;
}


public void setLastName(String lastName) {
	LastName = lastName;
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


public Slice getSlicePayment() {
	return slicePayment;
}


public void setSlicePayment(Slice slicePayment) {
	this.slicePayment = slicePayment;
}


@Column(name="RIB")
private String RIB;


@OneToOne(mappedBy = "paymentSlice")
private Slice slicePayment;
}
