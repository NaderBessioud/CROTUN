package tn.esprit.CROTUN.Entities;

import java.util.List;

public class AgentAmount {

	private String name;
	private List<CardAmount> series;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CardAmount> getSeries() {
		return series;
	}
	public void setSeries(List<CardAmount> series) {
		this.series = series;
	}
	public AgentAmount(String name, List<CardAmount> series) {
		super();
		this.name = name;
		this.series = series;
	}
	
	
}
