package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
@Entity
@Table(name="Complaint")
public class Complaint implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdL")
private long IdL;
@Column(name="Text")
private String Text;
@Column(name="Response")
private String Response;
@Column(name="status" ,nullable = false, columnDefinition = "bit default 0")
private boolean status;
private int num;

@Column(name="Date")
private Date Date;


@Enumerated(EnumType.STRING)
ComplaintSubject ComplaintSubject;
@JsonIgnore
@ManyToOne
private Customer customerComplaint;
@JsonIgnore
@ManyToOne
private Manager responderManager; 
@JsonIgnore
@ManyToOne
private Agent agentComplaint;

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public ComplaintSubject getComplaintSubject() {
	return ComplaintSubject;
}

public void setComplaintSubject(ComplaintSubject complaintSubject) {
	ComplaintSubject = complaintSubject;
}

public long getIdL() {
	return IdL;
}

public void setIdL(long idL) {
	IdL = idL;
}

public String getText() {
	return Text;
}

public void setText(String text) {
	Text = text;
}

public String getResponse() {
	return Response;
}

public void setResponse(String response) {
	Response = response;
}

public boolean getStatus() {
	return status;
}

public void setStatus(boolean statuss) {
	status = statuss;
}


/*public Timestamp getDate() {
	return Date;
}

public void setDate(Timestamp date) {
	Date = date;
}*/

public Date getDate() {
	return Date;
}

public void setDate(Date date) {
	Date = date;
}

public Customer getCustomerComplaint() {
	return customerComplaint;
}

public void setCustomerComplaint(Customer customerComplaint) {
	this.customerComplaint = customerComplaint;
}

public Manager getResponderManager() {
	return responderManager;
}

public void setResponderManager(Manager responderManager) {
	this.responderManager = responderManager;
}

public Agent getAgentComplaint() {
	return agentComplaint;
}

public void setAgentComplaint(Agent agentComplaint) {
	this.agentComplaint = agentComplaint;
}

public Complaint(long idL, String text, String response, boolean statuss, int num, Date date,
		tn.esprit.CROTUN.Entities.ComplaintSubject complaintSubject, Customer customerComplaint,
		Manager responderManager, Agent agentComplaint) {
	super();
	IdL = idL;
	Text = text;
	Response = response;
	status = statuss;
	this.num = num;
	Date = date;
	ComplaintSubject = complaintSubject;
	this.customerComplaint = customerComplaint;
	this.responderManager = responderManager;
	this.agentComplaint = agentComplaint;
}

@Override
public String toString() {
	return "Complaint [IdL=" + IdL + ", Text=" + Text + ", Response=" + Response + ", status=" + status + ", num=" + num
			+ ", Date=" + Date + ", ComplaintSubject=" + ComplaintSubject + ", customerComplaint=" + customerComplaint
			+ ", responderManager=" + responderManager + ", agentComplaint=" + agentComplaint + "]";
}

public Complaint() {
	super();
}



}
