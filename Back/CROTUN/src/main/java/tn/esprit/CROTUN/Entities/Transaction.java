package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdT")
private long IdT;
@Column(name="Type")
private String Type;
@Column(name="Amount")
private int Amount;
@Column(name="Archive")
private Boolean Archive;

@Column(name="Date")
private Timestamp Date;

@ManyToOne
private Account transactionAccount;
}
