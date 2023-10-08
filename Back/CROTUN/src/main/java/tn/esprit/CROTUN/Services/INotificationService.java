package tn.esprit.CROTUN.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.messaging.MessagingException;

import tn.esprit.CROTUN.Entities.Messages;
import tn.esprit.CROTUN.Entities.Notification;

public interface INotificationService {
	List<Notification> getNotificationagent(Long Idagent);
	List<Notification> getNotificationclient(Long Idclient);
	void deleteNotification(Long id);
	void addNotificationCustomer();
	void addNotificationCustomerLoan() throws MessagingException, IOException, javax.mail.MessagingException;
	void addNotificationCustomerAppointment();





}
