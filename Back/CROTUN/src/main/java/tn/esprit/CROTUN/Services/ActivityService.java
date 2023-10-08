package tn.esprit.CROTUN.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.UserActivity;
import tn.esprit.CROTUN.Repositories.ActivityRepository;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.DeviceRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;

@Service
public class ActivityService {
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	AgentRepository  agentRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	InvestorRespository investorRespository;
	
	public UserActivity saveVisitorInfo(UserActivity visitor) {
		return activityRepository.save(visitor);
	}
	
	public List<UserActivity> getUserDeviceActivity(String user,String ip){
		List<String> result=new ArrayList<>();
		
		List<UserActivity> activities=activityRepository.findByUserAndIp(user, ip);
		System.out.print("houuuuuuuuuuuuni "+user);
	for(UserActivity activity:activities) {
		String act="URL : "+activity.getUrl()+" Page : "+activity.getPage()+" ,query String : "+activity.getQueryString()+" at "+activity.getLoggedTime()
		+" with user agent : "+activity.getUserAgent();
		System.out.print(activity);
		result.add(act);
		
	}
	return activities;
	}
	

}
