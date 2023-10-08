package tn.esprit.CROTUN.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import tn.esprit.CROTUN.Entities.Complaint;
import tn.esprit.CROTUN.Services.IComplaintService;
import tn.esprit.CROTUN.requestApiForm.MessageResponse;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	IComplaintService complaintService;

	// http://localhost:8082/ComplaintMng/complaint/retrieve-all-complaints
	@GetMapping("/retrieve-all-complaints")
	@ResponseBody
	public List<Complaint> getComplaints() {
	List<Complaint> listProduits = complaintService.retrieveAllComplaints();
	return listProduits;
	}

	
	// http://localhost:8082/ComplaintMng/complaint/retrieve-complaint/8
	@GetMapping("/retrieve-complaint")
	@ResponseBody
	public Complaint retrieveComplaint(@RequestParam("complaintid") Long complaintId) {

	return complaintService.retrieveComplaint(complaintId);
	}

	
	@PostMapping("/create")
	@ResponseBody
	public void createReclamation(@RequestBody Complaint c,@RequestParam("num") int num, @RequestParam(value="complaintSubject") String complaintSubject , @RequestParam("idmanager") Long idm, @PathVariable("idcustomer") Long idc, @RequestParam("idagent")Long ida)
	{
		complaintService.addComplaint(c, idm, idc, ida,num,complaintSubject);

		
	}
	
	// http://localhost:8082/ComplaintMng/complaint/add-complaint
	@PostMapping("/add-complaint")
	@ResponseBody
	public  ResponseEntity<?> create(@RequestBody Complaint c,@RequestParam("num") int num, @RequestParam(value="complaintSubject") String complaintSubject , @RequestParam("idmanager") Long idm, @PathVariable("idcustomer") Long idc, @RequestParam("idagent")Long ida) {

		System.out.print(c);
		String [] wordsFromText = c.getText().split(" ");
		
		 if (complaintService.badWordsValidation(wordsFromText))
		 {
	        //   return ResponseEntity.ok(complaintService.addComplaint(c,idm,idc,ida));
			 return ResponseEntity.ok(complaintService.createReclamation(c,idm,idc,ida, num, complaintSubject));

		 
		
			
		}
		 else
		 {
	            return ResponseEntity.badRequest().body(new MessageResponse("bad words try again"));
 
		 }
	}
	/*public Complaint addComplaint(@RequestBody Complaint c, @PathVariable("id-manager")Long idm, @PathVariable("id-customer")Long idc, @PathVariable("id-agent")Long ida)
	{
		public  ResponseEntity<?> create(@RequestBody Reclamation rec, @PathVariable Long userid, @PathVariable Long idoffre) {
			String [] wordsFromText = rec.getDescription().split(" ");
		 if (complaintService.badWordsValidation(wordsFromText))
		 {
	            return ResponseEntity.ok(complaintService.create(c,idm,idc,ida));

		 
		
			
		}
		 else
		 {
	            return ResponseEntity.badRequest().body(new MessageResponse("klem 5ayeb"));
 
		 }
	Complaint complaint= complaintService.addComplaint(c,idm,idc,ida);
	return complaint;
	}*/
	
	
	// http://localhost:8082/ComplaintMng/complaint/remove-complaint/{complaint-id}
	@DeleteMapping("/remove-complaint")
	@ResponseBody
	public void removeComplaint(@RequestParam("complaintid") Long complaintId) {

	complaintService.deleteComplaint(complaintId);
	}
	
	
	// http://localhost:8082/ComplaintMng/complaint/modify-complaint
	@PutMapping("/create1")
	@ResponseBody
	public Complaint modifyComplaint(@RequestBody Complaint complaint, @RequestParam("idmanager")Long idm, @RequestParam("idcustomer")Long idc, @RequestParam("idagent")Long ida,@RequestParam(value="complaintSubject") String complaintSubject,@RequestParam(value="text")String text) {
	return complaintService.updateComplaint(complaint,idm,idc,ida,complaintSubject, text);
	}
	
	@PutMapping("/traiter")
	@ResponseBody
	void  traiter(@RequestParam("idreq") Long idreq,@RequestParam(value = "Rep", required = true) String  reponse)

	{
		complaintService.traiter(idreq, reponse);
	}
	
	@GetMapping("/complaintsSystemDecision")
	@ResponseBody
	public void complaintsSystemDecision(@RequestParam("idmanager")Long IdM) {
		complaintService.complaintsSystemDecision(IdM);
	}
	
	@GetMapping("/TypeOfComplaint")
	@ResponseBody
	public String nb_reclmation_traite(@RequestParam("id") Long id){

		return complaintService.GetType(id);
	}
	
	@GetMapping("/NumberTreated")
	@ResponseBody
	public float getCountTreated() {
	float count = complaintService.TreatedComplaint();
	return count;
	}
	
}
