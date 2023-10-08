package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="Customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdC")
private long IdC;
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
private Date CreatedAt;
@Temporal(TemporalType.DATE)
@Column(name="birthDate")
private Date birthDate;
@Column(name="Email")
private String email;
@Column(name="UserName")
private String userName;
@Column(name="Password")
private String password;
@Column(name="Salary")
private String salary;
@Column(name="Job")
private String job;
@Column(name="SocialRate")
private String socialRate;
@Column(name="NumberChildren")
private int numberChildren;
@Column(name="Image")
private String image;
@Column(name="enabled")
private boolean enabled;
@Column(name="status")
private boolean status;
@Transient
private String token;
@Column(name="banned")
private Boolean banned;
@Column(name="bannedPeriode")
private Date bannedPeriode;

@JsonIgnore
@OneToMany(mappedBy = "customerCard")
private Set<CreditCard> CardsCust;
@JsonIgnore
@OneToOne(mappedBy = "customerToken")
private RefreshToken customerToken;

@JsonIgnore
@Column(name="Age")
private int Age;
@Column(name="Sex")
private String Sex;
@Column(name="Housing")
@Enumerated(EnumType.STRING)
private Housing housing;
@Column(name="SavingAccounts")
private float SavingAccounts;
@JsonIgnore
@OneToMany(mappedBy = "CustomerLoans")
private Set<Loan> LoansCust;
@JsonIgnore
@OneToOne
private Account accountCustomer;

private double sold;
@JsonIgnore
@OneToMany(mappedBy = "customerComplaint")
private Set<Complaint> complaints;
@JsonIgnore
@OneToMany(mappedBy = "customerNotif")
private Set<Notification> notifications;
@JsonIgnore
@OneToMany(mappedBy = "customerSender")
private Set<Messages> messagesS;
@JsonIgnore
@OneToMany(mappedBy = "customerReceiver")
private Set<Messages> messagesR;
@JsonIgnore
@OneToMany(mappedBy = "customerApp")
private Set<Appointment> appointments;


@JsonIgnore
@OneToMany(mappedBy = "customerAvailbility")
private Set<Availablity> availbilitys;
@JsonIgnore
@OneToOne(mappedBy = "customerEmail")
private EmailVerificationToken emailCustomer;
@JsonIgnore
@OneToOne(mappedBy = "customerPass")
private PasswordResetToken passCustomer;
@JsonIgnore
@OneToMany(mappedBy = "customerDevice")
private Set<DeviceMetadata> deviceCustomer;

public int getAge() {
	return Age;
}


public void setAge(int age) {
	Age = age;
}


public String getSex() {
	return Sex;
}


public void setSex(String sex) {
	Sex = sex;
}


public Housing getHousing() {
	return housing;
}


public void setHousing(Housing housing) {
	this.housing = housing;
}


public float getSavingAccounts() {
	return SavingAccounts;
}


public void setSavingAccounts(float savingAccounts) {
	SavingAccounts = savingAccounts;
}


public Customer() {
	
}


public boolean isStatus() {
	return status;
}


public void setStatus(boolean status) {
	this.status = status;
}


public EmailVerificationToken getEmailCustomer() {
	return emailCustomer;
}


public void setEmailCustomer(EmailVerificationToken emailCustomer) {
	this.emailCustomer = emailCustomer;
}


public long getIdC() {
	return IdC;
}


public void setIdC(long idC) {
	IdC = idC;
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
	return CreatedAt;
}


public void setCreatedAt(Date createdAt) {
	CreatedAt = createdAt;
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


public String getSalary() {
	return salary;
}


public void setSalary(String salary) {
	this.salary = salary;
}


public String getJob() {
	return job;
}


public void setJob(String job) {
	this.job = job;
}


public String getSocialRate() {
	return socialRate;
}


public void setSocialRate(String socialRate) {
	this.socialRate = socialRate;
}


public int getNumberChildren() {
	return numberChildren;
}


public void setNumberChildren(int numberChildren) {
	this.numberChildren = numberChildren;
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


public Set<CreditCard> getCardsCust() {
	return CardsCust;
}


public void setCardsCust(Set<CreditCard> cardsCust) {
	CardsCust = cardsCust;
}

@JsonIgnore
public Set<Loan> getLoansCust() {
	return LoansCust;
}


public void setLoansCust(Set<Loan> loansCust) {
	LoansCust = loansCust;
}


public Account getAccountCustomer() {
	return accountCustomer;
}


public void setAccountCustomer(Account accountCustomer) {
	this.accountCustomer = accountCustomer;
}


public Set<Complaint> getComplaints() {
	return complaints;
}


public void setComplaints(Set<Complaint> complaints) {
	this.complaints = complaints;
}


public Set<Notification> getNotifications() {
	return notifications;
}


public void setNotifications(Set<Notification> notifications) {
	this.notifications = notifications;
}


public Set<Messages> getMessagesS() {
	return messagesS;
}


public void setMessagesS(Set<Messages> messagesS) {
	this.messagesS = messagesS;
}


public Set<Messages> getMessagesR() {
	return messagesR;
}


public void setMessagesR(Set<Messages> messagesR) {
	this.messagesR = messagesR;
}


public Set<Appointment> getAppointments() {
	return appointments;
}


public void setAppointments(Set<Appointment> appointments) {
	this.appointments = appointments;
}


public Set<Availablity> getAvailbilitys() {
	return availbilitys;
}


public void setAvailbilitys(Set<Availablity> availbilitys) {
	this.availbilitys = availbilitys;
}



public String getToken() {
	return token;
}


public void setToken(String token) {
	this.token = token;
}


public Boolean getBanned() {
	return banned;
}


public void setBanned(Boolean banned) {
	this.banned = banned;
}


public Date getBannedPeriode() {
	return bannedPeriode;
}


public void setBannedPeriode(Date bannedPeriode) {
	this.bannedPeriode = bannedPeriode;
}





public Customer(long idC, String cIN, String firstName, String lastName, String address, Date createdAt,
		Date birthDate, String email, String userName, String password, String salary, String job, String socialRate,
		int numberChildren, String image, boolean enabled, int age, String sex, Housing housing, float savingAccounts,
		Set<CreditCard> cardsCust, Set<Loan> loansCust, Account accountCustomer, Set<Complaint> complaints,
		Set<Notification> notifications, Set<Messages> messagesS, Set<Messages> messagesR,
		Set<Appointment> appointments, Set<Availablity> availbilitys) {
	super();
	IdC = idC;
	CIN = cIN;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	CreatedAt = createdAt;
	this.birthDate = birthDate;
	this.email = email;
	this.userName = userName;
	this.password = password;
	this.salary = salary;
	this.job = job;
	this.socialRate = socialRate;
	this.numberChildren = numberChildren;
	this.image = image;
	this.enabled = enabled;
	Age = age;
	Sex = sex;
	this.housing = housing;
	SavingAccounts = savingAccounts;
	CardsCust = cardsCust;
	LoansCust = loansCust;
	this.accountCustomer = accountCustomer;
	this.complaints = complaints;
	this.notifications = notifications;
	this.messagesS = messagesS;
	this.messagesR = messagesR;
	this.appointments = appointments;
	this.availbilitys = availbilitys;
}


public double getSold() {
	return sold;
}


public void setSold(double sold) {
	this.sold = sold;
}









}
