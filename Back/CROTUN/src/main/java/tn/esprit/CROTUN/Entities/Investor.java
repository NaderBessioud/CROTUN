package tn.esprit.CROTUN.Entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="Investor")
public class Investor  implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdInvestor")
private long IdInvestor;
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
@Column(name="Password")
private String password;
@Column(name="Image")
private String Image;
@Column(name="UserName")
private String userName;

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



@OneToMany(cascade = CascadeType.ALL,mappedBy="InvestorInvest")
private Set<InvestmentOffer> InvestOffer;

@OneToMany(cascade = CascadeType.ALL,mappedBy="Investors")
private Set<Investment> Investments ;



@JsonIgnore
@OneToOne(mappedBy = "investorEmail")
private EmailVerificationToken emailInvestor;

@JsonIgnore
@OneToOne(mappedBy = "investorPass")
private PasswordResetToken passInvestor;

@JsonIgnore
@OneToOne(mappedBy = "investorToken", fetch = FetchType.LAZY)
private RefreshToken tokenInvestor;

@JsonIgnore
@OneToMany(mappedBy = "investorDevice")
private Set<DeviceMetadata> devicesInvestor;




public long getIdInvestor() {
	return IdInvestor;
}

public void setIdInvestor(long idInvestor) {
	IdInvestor = idInvestor;
}

public String getCIN() {
	return CIN;
}

public void setCIN(String cIN) {
	CIN = cIN;
}


public String getFristName() {
	return firstName;
}

public void setFristName(String fristName) {
	firstName = fristName;
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

public Date getAreatedAt() {
	return createdAt;
}

public void setAreatedAt(Date areatedAt) {
	this.createdAt = areatedAt;
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

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;

}

public String getImage() {
	return Image;
}

public void setImage(String image) {
	Image = image;
}



@JsonIgnore
public Set<InvestmentOffer> getInvestOffer() {
	return InvestOffer;
}


public void setInvestOffer(Set<InvestmentOffer> investOffer) {
	InvestOffer = investOffer;
}









public Investor() {
	super();
	// TODO Auto-generated constructor stub
}

public Investor(long idInvestor, String cIN, String fristName, String lastName, String address, Date createdAt,
		Date birthDate, String email, String password, String image, Set<InvestmentOffer> investOffer,
		Set<Investment> investments) {
	super();
	IdInvestor = idInvestor;
	CIN = cIN;
	firstName = fristName;
	lastName = lastName;
	address = address;
	createdAt = createdAt;
	birthDate = birthDate;
	email = email;
	password = password;
	Image = image;
	InvestOffer = investOffer;
	Investments = investments;
}





public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public EmailVerificationToken getEmailInvestor() {
	return emailInvestor;
}

public void setEmailInvestor(EmailVerificationToken emailInvestor) {
	this.emailInvestor = emailInvestor;
}

public PasswordResetToken getPassInvestor() {
	return passInvestor;
}

public void setPassInvestor(PasswordResetToken passInvestor) {
	this.passInvestor = passInvestor;

}

public RefreshToken getTokenInvestor() {
	return tokenInvestor;
}

public void setTokenInvestor(RefreshToken tokenInvestor) {
	this.tokenInvestor = tokenInvestor;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
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





}