package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

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
@Table(name="InvestmentOffer")
public class InvestmentOffer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idino")
	private long idino;
	@Column(name="type")
	private String type;
	@Column(name="description")
	private String description;
	@Column(name="price")
	private float price;
	@Column(name="dateSell")
	private LocalDate dateSell;
	@Column(name="dateCreated")
	private LocalDate dateCreated;
	@Column(name="status")
	private boolean status;
	
	
	@ManyToOne
	Manager managerInvOffer ;
	
	@ManyToOne
	Investor InvestorInvest;
	
	
	public long getIdino() {
		return idino;
	}
	public void setIdino(long idino) {
		this.idino = idino;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public LocalDate getDateSell() {
		return dateSell;
	}
	public void setDateSell(LocalDate dateSell) {
		this.dateSell = dateSell;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@JsonIgnore
	public Manager getManagerInvOffer() {
		return managerInvOffer;
	}
	public void setManagerInvOffer(Manager managerInvOffer) {
		this.managerInvOffer = managerInvOffer;
	}
	
	@JsonIgnore
	public Investor getInvestorInvest() {
		return InvestorInvest;
	}
	public void setInvestorInvest(Investor investorInvest) {
		InvestorInvest = investorInvest;
	}
	@Override
	public String toString() {
		return "InvestmentOffer [idino=" + idino + ", type=" + type + ", description=" + description + ", price="
				+ price + ", dateSell=" + dateSell + ", dateCreated=" + dateCreated + ", status=" + status
				+ ", managerInvOffer=" + managerInvOffer + ", InvestorInvest=" + InvestorInvest + "]";
	}
	public InvestmentOffer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvestmentOffer(long idino, String type, String description, float price, LocalDate dateSell,
			LocalDate dateCreated, boolean status, Manager managerInvOffer, Investor investorInvest) {
		super();
		this.idino = idino;
		this.type = type;
		this.description = description;
		this.price = price;
		this.dateSell = dateSell;
		this.dateCreated = dateCreated;
		this.status = status;
		this.managerInvOffer = managerInvOffer;
		InvestorInvest = investorInvest;
	}

	
	
	
	
	
	
}
