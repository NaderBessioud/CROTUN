package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Manager")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdM")
private long IdM;
@Column(name="CIN")
private String CIN;
@Column(name="FirstName")
private String firstName;
@Column(name="LastName")
private String lastName;
@Column(name="Address")
private String address;

@Temporal(TemporalType.DATE)
@Column(name="CreatedAt")
private Date createdAt;
@Temporal(TemporalType.DATE)
@Column(name="BirthDate")
private Date birthDate;
@Column(name="Email")
private String email;
@Column(name="UserName")
private String userName;
@Column(name="Password")
private String password;
@Column(name="Image")
private String image;
@Column(name="enabled")
private boolean enabled;
@Column(name="status")
private boolean status;

@Transient
private String token;

@OneToOne(mappedBy = "managerToken")
private RefreshToken managerToken;
@JsonIgnore
@OneToMany(mappedBy = "managerInvOffer")
private Set<InvestmentOffer> investmentsManager;


@OneToMany(mappedBy = "responderManager")
private Set<Complaint> comlaintsManager;

@JsonIgnore
@OneToOne(mappedBy = "managerEmail")
private EmailVerificationToken emailManager;

@OneToOne(mappedBy = "managerPass")
private PasswordResetToken passManager;

@OneToMany(mappedBy = "managerDevice")
private Set<DeviceMetadata> deviceManager;


public Manager() {

}


public boolean isStatus() {
	return status;
}


public void setStatus(boolean status) {
	this.status = status;
}


public EmailVerificationToken getEmailManager() {
	return emailManager;
}


public void setEmailManager(EmailVerificationToken emailManager) {
	this.emailManager = emailManager;
}


public long getIdM() {
	return IdM;
}


public void setIdM(long idM) {
	IdM = idM;
}


public String getCIN() {
	return CIN;
}


public void setCIN(String cIN) {
	CIN = cIN;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public Date getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}


public Date getBirthDate() {
	return birthDate;
}


public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getUserName() {
	return userName;
}


public void setUserName(String userName) {
	this.userName = userName;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getImage() {
	return image;
}


public void setImage(String image) {
	this.image = image;
}


public boolean isEnabled() {
	return enabled;
}


public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}


public Set<InvestmentOffer> getInvestmentsManager() {
	return investmentsManager;
}


public void setInvestmentsManager(Set<InvestmentOffer> investmentsManager) {
	this.investmentsManager = investmentsManager;
}


public Set<Complaint> getComlaintsManager() {
	return comlaintsManager;
}


public void setComlaintsManager(Set<Complaint> comlaintsManager) {
	this.comlaintsManager = comlaintsManager;
}


public String getToken() {
	return token;
}


public void setToken(String token) {
	this.token = token;
}




}
