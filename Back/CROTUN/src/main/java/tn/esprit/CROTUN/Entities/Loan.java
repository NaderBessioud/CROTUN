package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Loan")
public class Loan implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdL")
private Long IdL;
@Column(name="TotalAmount")
private int TotalAmount;
@Column(name="LoanRef")
private String LoanRef;
@Column(name="LoanPeriodInMonths")
private int LoanPeriodInMonths;
@Column(name="Interest")
private double Interest;
@Column(name="DateStartLoan")
private LocalDate DateStartLoan;
@Column(name="DateEndLoan")
private LocalDate DateEndLoan;
@Column(name="RemainAmount")
private int RemainAmount;
@Column(name="PayedAmount")
private int PayedAmount;
@Column(name="StateL")
@Enumerated(EnumType.STRING)
private StateLoan StateL;
@Column(name="Archive")
private boolean Archive;
@Column(name="loanPurpose")
@Enumerated(EnumType.STRING)
private LoanPurpose loanPurpose;
@Column(name="Score")
private double Score;


@ManyToOne
private Agent agentsLoan;
@ManyToOne
private Customer CustomerLoans;
@OneToOne(cascade = CascadeType.ALL)
private Guarantee guranteeLoan;

@OneToMany(mappedBy = "loanSlice")
private Set<Slice> slices;

@OneToMany(cascade = CascadeType.ALL, mappedBy="LoansDetail")
private Set<DetailLoan> LoansDetails;
public Long getIdL() {
	return IdL;
}


public void setIdL(Long idL) {
	IdL = idL;
}


public int getTotalAmount() {
	return TotalAmount;
}


public void setTotalAmount(int totalAmount) {
	TotalAmount = totalAmount;
}


public int getLoanPeriodInMonths() {
	return LoanPeriodInMonths;
}


public void setLoanPeriodInMonths(int loanPeriodInMonths) {
	LoanPeriodInMonths = loanPeriodInMonths;
}


public double getInterest() {
	return Interest;
}


public void setInterest(double interest) {
	Interest = interest;
}

public void setScore(double score) {
	Score = score;
}

public double getScore() {
	return Score;
}

public LoanPurpose getLoanPurpose() {
	return loanPurpose;
}


public void setLoanPurpose(LoanPurpose loanPurpose) {
	this.loanPurpose = loanPurpose;
}

public LocalDate getDateStartLoan() {
	return DateStartLoan;
}


public void setDateStartLoan(LocalDate localDate) {
	DateStartLoan = localDate;
}


public LocalDate getDateEndLoan() {
	return DateEndLoan;
}


public void setDateEndLoan(LocalDate localDate) {
	DateEndLoan = localDate;
}


public int getRemainAmount() {
	return RemainAmount;
}


public void setRemainAmount(int remainAmount) {
	RemainAmount = remainAmount;
}


public int getPayedAmount() {
	return PayedAmount;
}


public void setPayedAmount(int payedAmount) {
	PayedAmount = payedAmount;
}


public StateLoan getStateL() {
	return StateL;
}


public void setStateL(StateLoan stateL) {
	StateL = stateL;
}


public boolean isArchive() {
	return Archive;
}


public void setArchive(boolean archive) {
	Archive = archive;
}

@JsonIgnore
public Agent getAgentsLoan() {
	return agentsLoan;
}


public void setAgentsLoan(Agent agentsLoan) {
	this.agentsLoan = agentsLoan;
}


public Customer getCustomerLoans() {
	return CustomerLoans;
}


public void setCustomerLoans(Customer customerLoans) {
	CustomerLoans = customerLoans;
}


public Guarantee getGuranteeLoan() {
	return guranteeLoan;
}


public void setGuranteeLoan(Guarantee guranteeLoan) {
	this.guranteeLoan = guranteeLoan;
}

//@JsonManagedReference
@JsonIgnore
public Set<Slice> getSlices() {
	return slices;
}


public void setSlices(Set<Slice> slices) {
	this.slices = slices;
}

@JsonIgnore
public Set<DetailLoan> getLoansDetails() {
	return LoansDetails;
}


public void setLoansDetails(Set<DetailLoan> loansDetails) {
	LoansDetails = loansDetails;
}






public Loan(Long idL, int totalAmount, String loanRef, int loanPeriodInMonths, double interest, LocalDate dateStartLoan,
		LocalDate dateEndLoan, int remainAmount, int payedAmount, StateLoan stateL, boolean archive,
		LoanPurpose loanPurpose, double score, Agent agentsLoan, Customer customerLoans, Guarantee guranteeLoan,
		Set<Slice> slices, Set<DetailLoan> loansDetails) {
	super();
	IdL = idL;
	TotalAmount = totalAmount;
	LoanRef = loanRef;
	LoanPeriodInMonths = loanPeriodInMonths;
	Interest = interest;
	DateStartLoan = dateStartLoan;
	DateEndLoan = dateEndLoan;
	RemainAmount = remainAmount;
	PayedAmount = payedAmount;
	StateL = stateL;
	Archive = archive;
	this.loanPurpose = loanPurpose;
	Score = score;
	this.agentsLoan = agentsLoan;
	CustomerLoans = customerLoans;
	this.guranteeLoan = guranteeLoan;
	this.slices = slices;
	LoansDetails = loansDetails;
}


public Loan() {
	super();

}


public static long getSerialversionuid() {
	return serialVersionUID;
}


public String getLoanRef() {
	return LoanRef;
}


public void setLoanRef(String loanRef) {
	LoanRef = loanRef;
}



}