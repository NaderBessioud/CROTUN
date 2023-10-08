package tn.esprit.CROTUN.Services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Appointment;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Notification;
import tn.esprit.CROTUN.Entities.Slice;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.AppointmentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.LoanRepository;
import tn.esprit.CROTUN.Repositories.NotificationRepository;
import tn.esprit.CROTUN.Repositories.SliceRepository;
import tn.esprit.CROTUN.messagenotifapi.EmailSenderService;
import tn.esprit.CROTUN.messagenotifapi.Mail;
import tn.esprit.CROTUN.messagenotifapi.Smsrequest;
import tn.esprit.CROTUN.messagenotifapi.Smsservice;

@Service
public class NotificationService implements INotificationService {
	@Autowired
	NotificationRepository notifrep;
	@Autowired
	AgentRepository agentep;
	@Autowired
	CustomerRepository custrep;
	
	@Autowired
	SliceRepository slicerep;
	@Autowired
	LoanRepository loanrep;
	@Autowired
    private EmailSenderService emailService;
	@Autowired
	AppointmentRepository apprep;
	@Autowired
	Smsservice smserv;

	@Override
	public List<Notification> getNotificationagent(Long Idagent) {
		Agent g=agentep.findById(Idagent).orElse(null);
		return notifrep.getnotifagents(g);
		
	}

	@Override
	public List<Notification> getNotificationclient(Long Idclient) {
		Customer r=custrep.findById(Idclient).orElse(null);
		return notifrep.getnotifcliens(r);
		
		
	}

	@Override
	public void deleteNotification(Long id) {
notifrep.deleteById(id);		
	}

	@Override
	public void addNotificationCustomer() {
		List<Long> l=slicerep.retrievenotifloans();


		for (Long loana : l) {
			Notification n=new Notification() ;
			Date date=new Date();
			n.setDateEnvoi(date);
			Loan la=loanrep.findById(loana).orElse(null);
			n.setCustomerNotif(la.getCustomerLoans());
			n.setContenu("votre slice doit etre paye dans deux jours");
			notifrep.save(n);
		}
		
	}

	@Override
	public void addNotificationCustomerLoan() throws MessagingException, IOException, javax.mail.MessagingException {
		List<Long> l=slicerep.retrievepayedmonthloans();
		for (Long loana : l) {
			
			List <Slice> sl=slicerep.retrieveremainingslices(loana);
			Mail mail = new Mail();
	        mail.setFrom("crotun2022@gmail.com");//replace with your desired email
	        mail.setTo(loanrep.findById(loana).orElse(null).getCustomerLoans().getEmail());//replace with your desired email
	        System.out.println(loanrep.findById(loana).orElse(null).getCustomerLoans().getEmail());
	        mail.setSubject("Remaining loan Slices for "+loanrep.findById(loana).orElse(null).getCustomerLoans().getEmail());
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("slices", sl);
	        
	        mail.setProps(model);
	        emailService.sendEmail(mail);
	    }
		

		
	}
	@Override
	public void addNotificationCustomerAppointment() {
List <Appointment> la=apprep.retrievenotifappointmet();

		for (Appointment app : la) {
			Notification n=new Notification() ;
			Date date=new Date();
			n.setDateEnvoi(app.getDateApp());
			n.setCustomerNotif(app.getCustomerApp());
			n.setContenu("votre rendez vous aura lieu le "+app.getDateApp());
			notifrep.save(n);
			Smsrequest s= new Smsrequest("+21694312406","votre rendez vous aura lieu le"+app.getDateApp() );
			 smserv.sendsms(s);
		}
		
	}
	

}
