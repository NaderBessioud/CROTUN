package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="Slice")
public class Slice implements Serializable {
	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdS")
private long IdS;
@Column(name="Price")
private double price;
@Column(name="RepaymentDate")
private LocalDate RepaymentDate;
@Column(name="Verified")
private Boolean Verified;
public String getReference() {
	return Reference;
}

public void setReference(String reference) {
	Reference = reference;
}

@Column(name="Rip")
private int rip;
private String Reference;
@Column(name="Reference")
@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, mappedBy = "slice")
List<Media> recu;
public int getRip() {
	return rip;
}

public void setRip(int rip) {
	this.rip = rip;
}


@JsonIgnore
@ManyToOne
private Loan loanSlice;

@JsonIgnore
@OneToOne(mappedBy = "slicePen")

private Penalty penSlice;
@JsonIgnore
@OneToOne
private Payement paymentSlice;





public long getIdS() {
	return IdS;
}

public void setIdS(long idS) {
	IdS = idS;
}


public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public LocalDate getRepaymentDate() {
	return RepaymentDate;
}

public void setRepaymentDate(LocalDate localDate) {
	RepaymentDate = localDate;
}

public Boolean getVerified() {
	return Verified;
}

public void setVerified(Boolean verified) {
	Verified = verified;
}

public Loan getLoanSlice() {
	return loanSlice;
}

public void setLoanSlice(Loan loanSlice) {
	this.loanSlice = loanSlice;
}

public Penalty getPenSlice() {
	return penSlice;
}

public void setPenSlice(Penalty penSlice) {
	this.penSlice = penSlice;
}

public Payement getPaymentSlice() {
	return paymentSlice;
}

public void setPaymentSlice(Payement paymentSlice) {
	this.paymentSlice = paymentSlice;
}


public Slice(long idS, double price, LocalDate repaymentDate, Boolean verified, int rip, String reference,
		List<Media> recu, Loan loanSlice, Penalty penSlice, Payement paymentSlice) {
	super();
	IdS = idS;
	this.price = price;
	RepaymentDate = repaymentDate;
	Verified = verified;
	this.rip = rip;
	Reference = reference;
	this.recu = recu;
	this.loanSlice = loanSlice;
	this.penSlice = penSlice;
	this.paymentSlice = paymentSlice;
}

public Slice() {
	super();

}



}
