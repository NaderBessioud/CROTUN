package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Appointment")
public class Appointment implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAppointment")
	private long IdAppointment;
	@Column(name="DateApp")
	private Date DateApp;
	
	public Appointment(long idAppointment, Date dateApp, Customer customerApp, Agent agentApp) {
		super();
		IdAppointment = idAppointment;
		DateApp = dateApp;
		this.customerApp = customerApp;
		this.agentApp = agentApp;
	}

	public long getIdAppointment() {
		return IdAppointment;
	}

	public void setIdAppointment(long idAppointment) {
		IdAppointment = idAppointment;
	}

	public Date getDateApp() {
		return DateApp;
	}

	public void setDateApp(Date dateApp) {
		DateApp = dateApp;
	}

	public Appointment() {
		super();
	}

	public Customer getCustomerApp() {
		return customerApp;
	}

	public void setCustomerApp(Customer customerApp) {
		this.customerApp = customerApp;
	}

	public Agent getAgentApp() {
		return agentApp;
	}

	public void setAgentApp(Agent agentApp) {
		this.agentApp = agentApp;
	}

	@ManyToOne
	private Customer customerApp;
	
	@ManyToOne
	private Agent agentApp;

}
