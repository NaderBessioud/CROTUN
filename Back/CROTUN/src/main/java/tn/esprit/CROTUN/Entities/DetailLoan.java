package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity	
@Table(name="DetailLoan")
public class DetailLoan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdD")
	private long IdD;
	@Column(name="MontantRestant")
	private double MontantRestant;
	@Column(name="Interest")
	private double Interest;
	@Column(name="Amortissements")
	private double Amortissements;
	@Column(name="Mensualite")
	private double Mensualite;
	
	@ManyToOne()
	private Loan LoansDetail;
	
	public long getIdD() {
		return IdD;
	}

	public void setIdD(long idD) {
		IdD = idD;
	}

	public double getMontantRestant() {
		return MontantRestant;
	}

	public void setMontantRestant(double montantRestant) {
		MontantRestant = montantRestant;
	}

	public double getInterest() {
		return Interest;
	}

	public void setInterest(double interest) {
		Interest = interest;
	}

	public double getAmortissements() {
		return Amortissements;
	}

	public void setAmortissements(double amortissements) {
		Amortissements = amortissements;
	}

	public double getMensualite() {
		return Mensualite;
	}

	public void setMensualite(double mensualite) {
		Mensualite = mensualite;
	}

	public Loan getLoansDetail() {
		return LoansDetail;
	}

	public void setLoansDetail(Loan loansDetail) {
		LoansDetail = loansDetail;
	}

	public DetailLoan(long idD, double montantRestant, double interest, double amortissements, double mensualite,
			Loan loansDetail) {
		super();
		IdD = idD;
		MontantRestant = montantRestant;
		Interest = interest;
		Amortissements = amortissements;
		Mensualite = mensualite;
		LoansDetail = loansDetail;
	}

	public DetailLoan() {
		super();

	}
	
	
}
