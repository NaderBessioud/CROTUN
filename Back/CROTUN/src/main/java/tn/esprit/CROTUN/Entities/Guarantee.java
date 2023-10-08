package tn.esprit.CROTUN.Entities;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="Guarantee")
public class Guarantee implements Serializable{
	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdGu")
private long IdGu;
@Column(name="FristName")
private String FristName;
@Column(name="LastName")
private String LastName;
@Column(name="Address")
private String Address;
@Column(name="Image")
private String Image;
@Column(name="Type")
private String Type;
@Column(name="Description")
private String Description;

public long getIdGu() {
	return IdGu;
}

public void setIdGu(long idGu) {
	IdGu = idGu;
}

public String getFristName() {
	return FristName;
}

public void setFristName(String fristName) {
	FristName = fristName;
}

public String getLastName() {
	return LastName;
}

public void setLastName(String lastName) {
	LastName = lastName;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getImage() {
	return Image;
}

public void setImage(String image) {
	Image = image;
}

public String getType() {
	return Type;
}

public void setType(String type) {
	Type = type;
}

public String getDescription() {
	return Description;
}

public void setDescription(String description) {
	Description = description;
}
@JsonIgnore
public Loan getLoangur() {
	return loangur;
}

public void setLoangur(Loan loangur) {
	this.loangur = loangur;
}
@OneToOne(mappedBy = "guranteeLoan",cascade = CascadeType.ALL)
private Loan loangur;

public Guarantee(long idGu, String fristName, String lastName, String address, String image, String type,
		String description, Loan loangur) {
	super();
	IdGu = idGu;
	FristName = fristName;
	LastName = lastName;
	Address = address;
	Image = image;
	Type = type;
	Description = description;
	this.loangur = loangur;
}

public Guarantee() {
	super();

}


}
