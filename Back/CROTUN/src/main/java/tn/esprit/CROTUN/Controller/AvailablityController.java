package tn.esprit.CROTUN.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.Availablity;
import tn.esprit.CROTUN.Entities.InvestmentOffer;
import tn.esprit.CROTUN.Services.IAvailablityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/availablity")
public class AvailablityController {

	@Autowired
	IAvailablityService iAvaiService;
	

	
	@PostMapping("/addAvai")
	@ResponseBody
	public void addAvai(@RequestBody Availablity avai,@RequestParam("time1") LocalTime time1,@RequestParam("time2") LocalTime time2,@RequestParam("date") String date,@RequestParam("id") Long id) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  

		  //convert String to LocalDate
		  LocalDate localDate = LocalDate.parse(date, formatter);
		iAvaiService.addAvailablity(avai, time1, time2, localDate, id);
		
		
	}
	
	
	@PutMapping("/addAvaiCustomer")
	@ResponseBody
	public void addAvaiCustomer(@RequestParam("idA") Long idA,@RequestParam("idC") Long idC) {
		  		iAvaiService.addAvaiCustomer(idA,idC);
			
	}
	
	
	@GetMapping("/retrieveAllAvaibli")
	@ResponseBody
	public List<Availablity> getAvailablity() {
	List<Availablity> list = iAvaiService.retrieveAllAvailablity();
	return list;
	}
	
	@GetMapping("/dateAvai")
	@ResponseBody
	public List<String> getdateAvai() {
	List<String> list = iAvaiService.dateAvai();
	return list;
	}
	
	
	
	@GetMapping("/retrieveCustomerAvaibli")
	@ResponseBody
	public List<Availablity> getCustomerAvailablity(@RequestParam("idC") Long idC) {
	List<Availablity> list = iAvaiService.retrieveCustomerAvai(idC);
	return list;
	}
	
	@GetMapping("/retrieveAgentAvaibli")
	@ResponseBody
	public List<Availablity> getAgentAvailablity(@RequestParam("idC") Long idC) {
	List<Availablity> list = iAvaiService.retrieveAgentAvai(idC);
	return list;
	}
	
	
	
	
	
	
}
