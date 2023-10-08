package tn.esprit.CROTUN.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;

public class UserPrincipal implements UserDetails {
	
	private Object user;
	
	public UserPrincipal(Object user) {
		this.user = user;
	}
	
	private String getTypeUser() {
		if(user instanceof Agent) 
			return "agent";
		else if(user instanceof Customer)
			return "customer";
		else if(user instanceof Manager)
			return "manager";
		else if(user instanceof Investor)
			return "investor";
		return "";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		
		if(getTypeUser().equals("agent")) {
			authorities.add(new SimpleGrantedAuthority("agent"));
		}
		else if(getTypeUser().equals("customer")) {
			authorities.add(new SimpleGrantedAuthority("customer"));
			
		}
		else if(getTypeUser().equals("manager")) {
			System.out.print("Managerrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
			authorities.add(new SimpleGrantedAuthority("manager"));
		}
		else if(getTypeUser().equals("investor")){
			authorities.add(new SimpleGrantedAuthority("investor"));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		if(getTypeUser().equals("agent")) {
			Agent A=(Agent)user;
			return A.getPassword();
		}
		else if(getTypeUser().equals("customer")) {
			Customer C=(Customer)user;
			return C.getPassword();
		}
		else if(getTypeUser().equals("manager")) {
			Manager M=(Manager)user;
			return M.getPassword();
		}
		else if(getTypeUser().equals("investor")) {
			Investor I=(Investor)user;
			return I.getPassword();
		}
		else{
			return "";
		}
		
	}

	@Override
	public String getUsername() {
		
		
		if(getTypeUser().equals("agent")) {
			Agent A=(Agent)user;
			
			return A.getUserName();
		}
		else if(getTypeUser().equals("customer")) {
			Customer C=(Customer)user;
			
			return C.getUserName();
		}
		else if(getTypeUser().equals("manager")) {
			Manager M=(Manager)user;
			return M.getUserName();
		}
		else if(getTypeUser().equals("investor")) {
			Investor I=(Investor)user;
			return I.getUserName();
		}
		else{
			return "";
		}
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {

		if(getTypeUser().equals("agent")) {
			Agent A=(Agent)user;
			return A.isEnabled();
		}
		else if(getTypeUser().equals("customer")) {
			Customer C=(Customer)user;
			return C.isEnabled();
		}
		else if(getTypeUser().equals("manager")) {
			Manager M=(Manager)user;
			return M.isEnabled();
		}
		else if(getTypeUser().equals("investor")) {
			Investor I=(Investor)user;
			return I.isEnabled();
		}
		else{
			return false;
		}
		
	}

}
