package tn.esprit.CROTUN.Entities;

public class CustomerPenality {
	private Long idc;
	private String firstName;
	private String lastName;
	private Long penality;

	
	
	public CustomerPenality(Long idc,String firstName, String lastName, Long penality) {
		this.idc=idc;
		this.firstName = firstName;
		this.lastName = lastName;
		this.penality = penality;
	}

	
	
	public Long getPenality() {
		return penality;
	}
	public void setPenality(Long penality) {
		this.penality = penality;
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



	public Long getIdc() {
		return idc;
	}



	public void setIdc(Long idc) {
		this.idc = idc;
	}

	
	
	
	
	

}
