package tn.esprit.CROTUN.Entities;

public class CardAmount {
	private String name;
	private float value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public CardAmount(String name, float value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	

}
