package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="Penalty")
public class Penalty implements Serializable {
	
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="IdPen")
private long IdPen;
@Column(name="Amount")
private int Amount;
@Temporal(TemporalType.DATE)
@Column(name="RepaymentDate")
private Date RepaymentDate;

@OneToOne(optional = false)
@JoinColumn(name="slice_id")
private Slice slicePen;
}
