package tn.esprit.CROTUN.Event;

import java.util.Locale;

import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationEvent;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	
	
	
	private Agent agent;
	private Customer customer;
	private Manager manager;
	private Investor investor;
	
	public OnRegistrationCompleteEvent( Agent agent) {
		super(agent);
		
		this.agent = agent;
	}

	public OnRegistrationCompleteEvent(Customer customer) {
		super(customer);
		
		this.customer = customer;
	}

	public OnRegistrationCompleteEvent( Manager manager) {
		super(manager);
		this.manager = manager;
	}
	
	

	

	public OnRegistrationCompleteEvent( Investor investor) {
		super(investor);
		this.investor = investor;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
	
	
	
	
	
	
	
	

}
