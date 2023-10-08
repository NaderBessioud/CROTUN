package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Messages")
public class Messages implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMessage")
	private long idMessage;
	@Column(name="contenu")
	private String contenu;
	
	
	
	@Column(name="dateEnvoi")
	private Date dateEnvoi;
	@Column(name="Statut")
	private String Statut;
	@JsonIgnore
	
	@ManyToOne
	
	private Customer customerSender;
	@ManyToOne
	
	private Agent agentSender;
	@JsonIgnore
	@ManyToOne
	
	private Customer customerReceiver;
	@ManyToOne
	
	private Agent agentReceiver;
	
	public Messages(long idMessage, String contenu, Date dateEnvoi, String statut, Customer customerSender,
			Agent agentSender, Customer customerReceiver, Agent agentReceiver) {
		super();
		this.idMessage = idMessage;
		this.contenu = contenu;
		this.dateEnvoi = dateEnvoi;
		Statut = statut;
		this.customerSender = customerSender;
		this.agentSender = agentSender;
		this.customerReceiver = customerReceiver;
		this.agentReceiver = agentReceiver;
	}

	public long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(long idMessage) {
		this.idMessage = idMessage;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getStatut() {
		return Statut;
	}

	public void setStatut(String statut) {
		Statut = statut;
	}

	public Customer getCustomerSender() {
		return customerSender;
	}

	public void setCustomerSender(Customer customerSender) {
		this.customerSender = customerSender;
	}

	public Agent getAgentSender() {
		return agentSender;
	}

	public void setAgentSender(Agent agentSender) {
		this.agentSender = agentSender;
	}

	public Customer getCustomerReceiver() {
		return customerReceiver;
	}

	public void setCustomerReceiver(Customer customerReceiver) {
		this.customerReceiver = customerReceiver;
	}

	public Agent getAgentReceiver() {
		return agentReceiver;
	}

	public void setAgentReceiver(Agent agentReceiver) {
		this.agentReceiver = agentReceiver;
	}

	
	//pour les customers//@ManyToOne
	//pour les agents//@ManyToOne

	public Messages() {
		super();
	}

}
