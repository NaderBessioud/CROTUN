package tn.esprit.CROTUN.Entities;

public class InvestorInvestment {
	private String firstName;
	private String lastName;
	private float amount;
	public InvestorInvestment(String firstName, String lastName, float amount) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
	

}
