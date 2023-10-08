package tn.esprit.CROTUN.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import tn.esprit.CROTUN.Entities.Messages;
import tn.esprit.CROTUN.Entities.Notification;
import tn.esprit.CROTUN.Services.INotificationService;

import tn.esprit.CROTUN.messagenotifapi.Smsrequest;
import tn.esprit.CROTUN.messagenotifapi.Smsservice;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/notifications")


public class NotificationController {
	@Autowired
	Smsservice smsserv;
	@Autowired
	INotificationService notifservice;
	
	
	@GetMapping("/affichenotifclient")
	@ResponseBody
	public List<Notification> affichernotifc( @RequestParam("idclient") Long client) {
		return notifservice.getNotificationclient(client);
			
	}
	
	@GetMapping("/affichenotifagent")
	@ResponseBody
	public List<Notification> affichernotifa(@RequestParam("idagent") Long agent) {
		return notifservice.getNotificationagent(agent);
			
	}
	
	
	@DeleteMapping("/deletenootif/{idN}")
	@ResponseBody
	public void DeleteNotif(@PathVariable("idN") Long idNotif) {
			notifservice.deleteNotification(idNotif);}
	
	
	//@Scheduled(cron="0 0 1 * * *)")
	@PostMapping("/addNotifClient")
	@ResponseBody
	public void addnotift() {
		

notifservice.addNotificationCustomer();		
		
	}
	
	//@Scheduled(cron="0 0 23 * * MON-SUN)")
	@PostMapping("/addNotifClientpayed")
	@ResponseBody
	public void addnotifpayed() throws MessagingException, IOException, javax.mail.MessagingException {
		

notifservice.addNotificationCustomerLoan();		
		
	}
	@PostMapping("/addNotifClientmsg")
	@ResponseBody
	public void addnotifmsg()  {
		notifservice.addNotificationCustomerAppointment();
		
		
		

		
	}
	
	
	

}
