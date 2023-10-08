package tn.esprit.CROTUN.Entities;

public class AgentCard {
	private String firstName;
	private String lastName;
	private Long nbre;
	private float amount;
	public AgentCard(String firstName, String lastName, Long nbre,float amount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nbre = nbre;
		this.amount=amount;
		
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
	public Long getNbre() {
		return nbre;
	}
	public void setNbre(Long nbre) {
		this.nbre = nbre;
	}
	
	

}
