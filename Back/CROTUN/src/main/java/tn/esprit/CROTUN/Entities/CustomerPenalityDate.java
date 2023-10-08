package tn.esprit.CROTUN.Entities;

import java.util.Date;

public class CustomerPenalityDate {
	private Long idc;
	private String firstName;
	private String lastName;
	private int penality;
	private Date date;
	public CustomerPenalityDate(Long idc, String firstName, String lastName, int penality, Date date) {
		super();
		this.idc = idc;
		this.firstName = firstName;
		this.lastName = lastName;
		this.penality = penality;
		this.date = date;
	}
	public Long getIdc() {
		return idc;
	}
	public void setIdc(Long idc) {
		this.idc = idc;
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
	public int getPenality() {
		return penality;
	}
	public void setPenality(int penality) {
		this.penality = penality;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
