package tn.esprit.CROTUN.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Investment;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.AvailablityRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;




@Service
public class AvailablityService implements IAvailablityService {

@Autowired
AvailablityRepository AvaiRepo;
@Autowired
AgentRepository AgentRepo;
@Autowired
CustomerRepository CustomerRepo;


@Override
public void addAvailablity(Availablity Avai, LocalTime time1, LocalTime time2, LocalDate date, Long id) {
	Agent agent = AgentRepo.findById(id).orElse(null);
	LocalTime start = time1;
LocalTime end = time2;
long noOfHours = ChronoUnit.HOURS.between(start, end);
int numberhours = Math.toIntExact(noOfHours);
LocalDate localDate = LocalDate.now();
for(int i=0;i<numberhours;i++){
    Availablity avai = new Availablity();
    Avai = avai;
    Avai.setDateAva(date);
	Avai.setDateCreated(localDate);	
    Avai.setStartDate(start);
    end = start.plusHours(1);
    start = end;
    Avai.setEndHour(end);
    Avai.setAgentAvailbility(agent);
	AvaiRepo.save(Avai);
	
}
}


@Override
public void addAvaiCustomer(Long idA, Long idC) {
	Customer customer = CustomerRepo.findById(idC).orElse(null);
	Availablity avai = AvaiRepo.findById(idA).orElse(null);
	avai.setCustomerAvailbility(customer);
	AvaiRepo.save(avai);
	
}


@Override
public List<Availablity> retrieveAllAvailablity() {
	return (List<Availablity>) AvaiRepo.retrieveCurrentAvailbility();
}


@Override
public List<String> dateAvai() {
	return (List<String>) AvaiRepo.dateAvai();
}


@Override
public List<Availablity> retrieveCustomerAvai(Long id){			
	return (List<Availablity>) AvaiRepo.retrieveCustomerAvailblity(id);
}




@Override
public List<Availablity> retrieveAgentAvai(Long id){			
	return (List<Availablity>) AvaiRepo.retrieveAgentAvailblity(id);
}





//@Scheduled(fixedDelay = 60000)
@Override
public void deleteAvai() {
	LocalDate localDate = LocalDate.now();
	for (Availablity avai : AvaiRepo.findAll()) {
		LocalDate end = avai.getDateAva();
		if(localDate.isAfter(end) == true) {
			System.out.println(avai.getIdAvailblity() + " deleted");
			AvaiRepo.delete(avai);
			
		}
	}
}


@Override
public List<Availablity> retrieveAgentFreeApoint(Long id) {
	// TODO Auto-generated method stub
	return null;
}
	
}
