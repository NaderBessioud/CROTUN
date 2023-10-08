package tn.esprit.CROTUN.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="DeviceMetadata")
public class DeviceMetadata implements Serializable {
	private static final long serialVersionUID = 1L;
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 	@Column(name="IdDevice")
	    private Long idDevice;
	    @Column(name="deviceDetails")
	    private String deviceDetails;
	    @Column(name="location")
	    private String location;
	    @Column(name="lastLoggedIn")
	    private Date lastLoggedIn;
	    
	    @ManyToOne
	    private Agent agentDevice;
	    
	    @ManyToOne
	    private Customer customerDevice;
	    
	    @ManyToOne
	    private Manager managerDevice;
	    
	    @ManyToOne
	    private Investor investorDevice;

		public Long getIdDevice() {
			return idDevice;
		}

		public void setIdDevice(Long idDevice) {
			this.idDevice = idDevice;
		}

		public String getDeviceDetails() {
			return deviceDetails;
		}

		public void setDeviceDetails(String deviceDetails) {
			this.deviceDetails = deviceDetails;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public Date getLastLoggedIn() {
			return lastLoggedIn;
		}

		public void setLastLoggedIn(Date lastLoggedIn) {
			this.lastLoggedIn = lastLoggedIn;
		}

		public Agent getAgentDevice() {
			return agentDevice;
		}

		public void setAgentDevice(Agent agentDevice) {
			this.agentDevice = agentDevice;
		}

		public Customer getCustomerDevice() {
			return customerDevice;
		}

		public void setCustomerDevice(Customer customerDevice) {
			this.customerDevice = customerDevice;
		}

		public Manager getManagerDevice() {
			return managerDevice;
		}

		public void setManagerDevice(Manager managerDevice) {
			this.managerDevice = managerDevice;
		}

		public Investor getInvestorDevice() {
			return investorDevice;
		}

		public void setInvestorDevice(Investor investorDevice) {
			this.investorDevice = investorDevice;
		}

		public DeviceMetadata(String deviceDetails, String location, Agent agentDevice) {
			super();
			this.deviceDetails = deviceDetails;
			this.location = location;
			this.agentDevice = agentDevice;
		}

		public DeviceMetadata(String deviceDetails, String location, Customer customerDevice) {
			super();
			this.deviceDetails = deviceDetails;
			this.location = location;
			this.customerDevice = customerDevice;
		}

		public DeviceMetadata(String deviceDetails, String location, Manager managerDevice) {
			super();
			this.deviceDetails = deviceDetails;
			this.location = location;
			this.managerDevice = managerDevice;
		}

		public DeviceMetadata(String deviceDetails, String location, Investor investorDevice) {
			super();
			this.deviceDetails = deviceDetails;
			this.location = location;
			this.investorDevice = investorDevice;
		}

		public DeviceMetadata() {
			
			// TODO Auto-generated constructor stub
		}
		
		
	    
	    

}
