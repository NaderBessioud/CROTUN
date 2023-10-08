package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
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
@Table(name="Notification")

public class Notification implements Serializable {
public Notification(long idN, String contenu, Date dateEnvoi, String type, String image, Customer customerNotif,
			Agent agentNotif) {
		super();
		IdN = idN;
		this.contenu = contenu;
		this.dateEnvoi = dateEnvoi;
		this.type = type;
		this.image = image;
		this.customerNotif = customerNotif;
		this.agentNotif = agentNotif;
	}


public long getIdN() {
		return IdN;
	}

	public void setIdN(long idN) {
		IdN = idN;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Customer getCustomerNotif() {
		return customerNotif;
	}

	public void setCustomerNotif(Customer customerNotif) {
		this.customerNotif = customerNotif;
	}

	public Agent getAgentNotif() {
		return agentNotif;
	}

	public void setAgentNotif(Agent agentNotif) {
		this.agentNotif = agentNotif;
	}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdN")
private long IdN;
@Column(name="contenu")
private String contenu;
@Column(name="dateEnvoi")
private Date dateEnvoi;
public Notification() {
	super();
}

@Column(name="type")
private String type;
@Column(name="image")
private String image;

@ManyToOne
private Customer customerNotif;

@ManyToOne
private Agent agentNotif;
}


//pour les agents//@ManyToOne



