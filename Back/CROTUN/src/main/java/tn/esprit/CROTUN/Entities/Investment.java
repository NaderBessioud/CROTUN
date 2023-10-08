package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

import java.security.Timestamp;
import java.time.LocalDate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Investment")
public class Investment implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idIN")
	private long idIN;
	@Column(name="amount")
	private float amount;
	@Column(name="dateStart")
	private LocalDate dateStart;
	@Column(name="dateEnd")
	private LocalDate dateEnd;
	@Column(name="rate")
	private float rate ;
	@Column(name="duration")
	private int duration ;
	@Column(name="profit")
	private float profit;
	@Column(name="totalAmount")
	private float totalAmount;
	@Column(name="status",nullable = false, columnDefinition = "bit default 0")
	private boolean status;
	
	@ManyToOne
	Investor Investors;
	
	
	
	public long getIdIN() {
		return idIN;
	}
	public void setIdIN(long idIN) {
		this.idIN = idIN;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDate getDateStart() {
		return dateStart;
	}
	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@JsonIgnore
	public Investor getInvestors() {
		return Investors;
	}
	
	
	public void setInvestors(Investor investors) {
		Investors = investors;
	}
	@Override
	public String toString() {
		return "Investment [idIN=" + idIN + ", amount=" + amount + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", rate=" + rate + ", duration=" + duration + ", profit=" + profit + ", totalAmount=" + totalAmount
				+ ", status=" + status + ", Investors=" + Investors + "]";
	}
	public Investment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Investment(long idIN, float amount, LocalDate dateStart, LocalDate dateEnd, float rate, int duration,
			float profit, float totalAmount, boolean status, Investor investors) {
		super();
		this.idIN = idIN;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.rate = rate;
		this.duration = duration;
		this.profit = profit;
		this.totalAmount = totalAmount;
		this.status = status;
		Investors = investors;
	}
	

	
	

}
