package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Availablity")
public class Availablity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAvailblity")
	private long idAvailblity;
	@Column(name="dateCreated")
	private LocalDate dateCreated;
	@Column(name="dateAva")
	private LocalDate dateAva;
	@Column(name="startHour")
	private LocalTime startDate;
	@Column(name="endHour")
	private LocalTime endHour;
	
	@JsonIgnore
	@ManyToOne
	private Customer customerAvailbility;
	
	@JsonIgnore
	@ManyToOne
	private Agent agentAvailbility;

	public long getIdAvailblity() {
		return idAvailblity;
	}

	public void setIdAvailblity(long idAvailblity) {
		this.idAvailblity = idAvailblity;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateAva() {
		return dateAva;
	}

	public void setDateAva(LocalDate dateAva) {
		this.dateAva = dateAva;
	}

	public LocalTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}

	public LocalTime getEndHour() {
		return endHour;
	}

	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}

	public Customer getCustomerAvailbility() {
		return customerAvailbility;
	}

	public void setCustomerAvailbility(Customer customerAvailbility) {
		this.customerAvailbility = customerAvailbility;
	}

	public Agent getAgentAvailbility() {
		return agentAvailbility;
	}

	public void setAgentAvailbility(Agent agentAvailbility) {
		this.agentAvailbility = agentAvailbility;
	}

	@Override
	public String toString() {
		return "Availablity [idAvailblity=" + idAvailblity + ", dateCreated=" + dateCreated + ", dateAva=" + dateAva
				+ ", startDate=" + startDate + ", endHour=" + endHour + ", customerAvailbility=" + customerAvailbility
				+ ", agentAvailbility=" + agentAvailbility + "]";
	}

	public Availablity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Availablity(long idAvailblity, LocalDate dateCreated, LocalDate dateAva, LocalTime startDate,
			LocalTime endHour, Customer customerAvailbility, Agent agentAvailbility) {
		super();
		this.idAvailblity = idAvailblity;
		this.dateCreated = dateCreated;
		this.dateAva = dateAva;
		this.startDate = startDate;
		this.endHour = endHour;
		this.customerAvailbility = customerAvailbility;
		this.agentAvailbility = agentAvailbility;
	}

}
