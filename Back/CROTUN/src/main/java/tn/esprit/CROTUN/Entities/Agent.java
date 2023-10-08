package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Agent")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdA")
	private long IdA;
	@Column(name="CIN")
	private String CIN;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Address")
	private String address;

	
	public String getStatutt() {
		return statutt;
	}

	public void setStatutt(String statutt) {
		this.statutt = statutt;
	}

	@Column(name="statutt")
	private String statutt;


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
	@Column(name="Salary")
	private String salary;
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

	
	@OneToOne(mappedBy = "agentToken")
	private RefreshToken agentToken;
	@JsonIgnore
	@OneToMany(mappedBy = "agentCard")
	private Set<CreditCard> cards;
	@JsonIgnore
	@OneToMany(mappedBy = "agentsLoan")
	private Set<Loan> loansAgent;
	@JsonIgnore
	@OneToMany(mappedBy = "agentAccount")
	private Set<Account> accountsAgent;
	@JsonIgnore
	@OneToMany(mappedBy = "agentNotif")
	private Set<Notification> notificationsAgent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "agentSender")
	private Set<Messages> messagesAgentS;
	@JsonIgnore
	@OneToMany(mappedBy = "agentReceiver")
	private Set<Messages> messagesAgentR;

	@JsonIgnore
	@OneToMany(mappedBy = "agentApp")
	private Set<Appointment> appointmentAgent;
	@JsonIgnore
	@OneToMany(mappedBy = "agentAvailbility")
	private Set<Availablity> availbilityAgent;
	@JsonIgnore
	@OneToMany(mappedBy = "agentComplaint")
	private Set<Complaint> complaintAgent;
	
	@JsonIgnore
	@OneToOne(mappedBy = "agentEmail")
	private EmailVerificationToken emailAgent;
	@JsonIgnore
	@OneToOne(mappedBy = "agentPass")
	private PasswordResetToken passAgent;
	@JsonIgnore
	@OneToMany(mappedBy = "agentDevice")
	private Set<DeviceMetadata> deviceAgent;

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public EmailVerificationToken getEmailAgent() {
		return emailAgent;
	}

	public void setEmailAgent(EmailVerificationToken emailAgent) {
		this.emailAgent = emailAgent;
	}

	public long getIdA() {
		return IdA;
	}

	public void setIdA(long idA) {
		IdA = idA;
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

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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


	public Set<CreditCard> getCards() {
		return cards;
	}

	public void setCards(Set<CreditCard> cards) {
		this.cards = cards;
	}

	public Set<Loan> getLoansAgent() {
		return loansAgent;
	}

	public void setLoansAgent(Set<Loan> loansAgent) {
		this.loansAgent = loansAgent;
	}

	public Set<Account> getAccountsAgent() {
		return accountsAgent;
	}

	public void setAccountsAgent(Set<Account> accountsAgent) {
		this.accountsAgent = accountsAgent;
	}

	public Set<Notification> getNotificationsAgent() {
		return notificationsAgent;
	}

	public void setNotificationsAgent(Set<Notification> notificationsAgent) {
		this.notificationsAgent = notificationsAgent;
	}

	public Set<Messages> getMessagesAgentS() {
		return messagesAgentS;
	}

	public void setMessagesAgentS(Set<Messages> messagesAgentS) {
		this.messagesAgentS = messagesAgentS;
	}

	public Set<Messages> getMessagesAgentR() {
		return messagesAgentR;
	}

	public void setMessagesAgentR(Set<Messages> messagesAgentR) {
		this.messagesAgentR = messagesAgentR;
	}

	public Set<Appointment> getAppointmentAgent() {
		return appointmentAgent;
	}

	public void setAppointmentAgent(Set<Appointment> appointmentAgent) {
		this.appointmentAgent = appointmentAgent;
	}

	public Set<Availablity> getAvailbilityAgent() {
		return availbilityAgent;
	}

	public void setAvailbilityAgent(Set<Availablity> availbilityAgent) {
		this.availbilityAgent = availbilityAgent;
	}

	public Set<Complaint> getComplaintAgent() {
		return complaintAgent;
	}

	public void setComplaintAgent(Set<Complaint> complaintAgent) {
		this.complaintAgent = complaintAgent;
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

	public Agent() {
		
	}

	public Agent(long idA, String cIN, String firstName, String lastName, String address, Date createdAt,
			Date birthDate, String email, String userName, String password, String salary, String image,
			boolean enabled, Set<CreditCard> cards, Set<Loan> loansAgent, Set<Account> accountsAgent,
			Set<Notification> notificationsAgent, Set<Messages> messagesAgentS, Set<Messages> messagesAgentR,
			Set<Appointment> appointmentAgent, Set<Availablity> availbilityAgent, Set<Complaint> complaintAgent) {
		super();
		IdA = idA;
		CIN = cIN;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.createdAt = createdAt;
		this.birthDate = birthDate;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.salary = salary;
		this.image = image;
		this.enabled = enabled;
		this.cards = cards;
		this.loansAgent = loansAgent;
		this.accountsAgent = accountsAgent;
		this.notificationsAgent = notificationsAgent;
		this.messagesAgentS = messagesAgentS;
		this.messagesAgentR = messagesAgentR;
		this.appointmentAgent = appointmentAgent;
		this.availbilityAgent = availbilityAgent;
		this.complaintAgent = complaintAgent;
	}

}