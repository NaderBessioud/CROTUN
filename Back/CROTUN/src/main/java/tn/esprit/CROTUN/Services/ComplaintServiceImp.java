package tn.esprit.CROTUN.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Complaint;
import tn.esprit.CROTUN.Entities.ComplaintSubject;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.ComplaintRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;
import tn.esprit.CROTUN.Entities.RestrictWord;

@Service
public class ComplaintServiceImp implements IComplaintService{
	@Autowired
	ComplaintRepository complaintrepository;
	@Autowired
	AgentRepository agentrepository;
	@Autowired
	ManagerRepository managerrepository;
	@Autowired
	CustomerRepository customerrepository;
	@Autowired
	RestrictWordService restrictWordService;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public List<Complaint> retrieveAllComplaints() {
		return (List<Complaint>) complaintrepository.findAll();
	}

	@Override
	public Complaint addComplaint(Complaint c, Long IdM, Long IdC, Long IdA, int num, String complaintSubject) {

		Manager mg = managerrepository.findById(IdM).orElse(null);
		Agent ag = agentrepository.findById(IdA).orElse(null);
		Customer cr = customerrepository.findById(IdC).orElse(null);
		c.setAgentComplaint(ag);
		c.setCustomerComplaint(cr);
		c.setResponderManager(mg);
		c.setDate(new Date());
		ComplaintSubject myVar = ComplaintSubject.ReportProfile;
		String Var = "";
		
	    switch(complaintSubject) {
	      case "Reportprofile":
	    	  myVar = ComplaintSubject.ReportProfile;
	    	  Var = "profile";
	    	
	        break;
	      
	      case "Reportappointment":
	    	   myVar = ComplaintSubject.ReportAppointment;
	    	   Var = "appointment";
	        break;
	      
	    }
	    c.setComplaintSubject(myVar);

		complaintrepository.save(c);
		return c;
	}

	@Override
	public void deleteComplaint(Long id) {
		complaintrepository.deleteById(id);
		
	}

	@Override
	public Complaint updateComplaint(Complaint c, Long IdM, Long IdC, Long IdA,String complaintSubject,String text) {

		Manager mg = managerrepository.findById(IdM).orElse(null);
		Agent ag = agentrepository.findById(IdA).orElse(null);
		Customer cr = customerrepository.findById(IdC).orElse(null);
		c.setAgentComplaint(ag);
		c.setCustomerComplaint(cr);
		c.setResponderManager(mg);
		c.setText(text);
		c.setDate(new Date());
		ComplaintSubject myVar = ComplaintSubject.ReportProfile;
		String Var = "";
		
	    switch(complaintSubject) {
	      case "Reportprofile":
	    	  myVar = ComplaintSubject.ReportProfile;
	    	  Var = "profile";
	    	
	        break;
	      
	      case "Reportappointment":
	    	   myVar = ComplaintSubject.ReportAppointment;
	    	   Var = "appointment";
	        break;
	      
	    }		c.setComplaintSubject(myVar);

		complaintrepository.save(c);
		return c;
	}

	@Override
	public Complaint retrieveComplaint(Long id) {
		Complaint complaint = complaintrepository.findById(id).orElse(null);
		return complaint;
	}

	@Override
	public Boolean badWordsValidation(String[] wordsFromText) {
		List<RestrictWord> restrictWords = restrictWordService.findAll();
		Boolean thatsOk = true;
		if (!restrictWords.isEmpty())
			for (int i = 0; i < wordsFromText.length; i++) {
				String wordFromText = wordsFromText[i];
				if (restrictWords.stream().filter(word -> word.getWord().equalsIgnoreCase(wordFromText)).count() > 0) {
					thatsOk = false;
					break;
				}
			}
		return thatsOk;
	}
	
	@Override
	public void traiter(Long rec, String reponse) {
		// TODO Auto-generated method stub
		Complaint recTotraiter = complaintrepository.findById(rec).orElse(null);
		//recTotraiter.setEtat("traiter");
		recTotraiter.setResponse(reponse);
		recTotraiter.setStatus(true);

		complaintrepository.save(recTotraiter);

	}

	@Override
	public Complaint createReclamation(Complaint c, Long IdM, Long IdC, Long IdA, int num, String complaintSubject) {
		//Complaint Complaint = new Complaint();
		//c.setText(Text);
		c.setNum(num);
		//Complaint.setResponse("hh");
		Manager mg = managerrepository.findById(IdM).orElse(null);
		Agent ag = agentrepository.findById(IdA).orElse(null);
		Customer cr = customerrepository.findById(IdC).orElse(null);
		c.setAgentComplaint(ag);
		c.setCustomerComplaint(cr);
		c.setResponderManager(mg);
	    c.setStatus(false);
	    System.out.println(c.getStatus());
		c.setDate(new Date());

		ComplaintSubject myVar = ComplaintSubject.ReportProfile;
		String Var = "";
		
	    switch(complaintSubject) {
	      case "Reportprofile":
	    	  myVar = ComplaintSubject.ReportProfile;
	    	  Var = "profile";
	    	
	        break;
	      
	      case "Reportappointment":
	    	   myVar = ComplaintSubject.ReportAppointment;
	    	   Var = "appointment";
	        break;
	      
	    }
	    c.setComplaintSubject(myVar);
	    Manager user = managerrepository.findById(IdC).orElse(null);
	    System.out.println("user");
	    System.out.println(user);
	    c.setResponderManager(user);
	    Set<Complaint> userReclamations = (Set<Complaint>) user.getComlaintsManager();
	    userReclamations.add(c);
	    /*
	    Customer user = customerrepository.findById(IdC).orElse(null);
	    System.out.println("user");
	    System.out.println(user);
	    c.setCustomerComplaint(user);
	    Set<Complaint> userReclamations = (Set<Complaint>) user.getComplaints();
	    userReclamations.add(c);
	    */
	    
	    complaintrepository.save(c);
	    return c;
	    
	   // String toEmail = user.getEmail();
	  //  String body = "we receive your Complaint about the "+Var+" as you said that is: "+c.getText()+" ."+"\\n"+" we will take your Complaint into consideration and make the necessary modifications";
	  //  String Subject="Complaint to "+Var+" Received";
	    //(String toEmail, String subject, String body)
	  //  EmailSenderService.sendEmail(toEmail, Subject, body);
	}
		
	
	public Integer[] MaxOccur(ArrayList<Integer> array){
		 int max = 0;
		 int pos = 0;
		 Integer[] MaxAndPosition = {0,0};
		 
			 for( int i = 0; i < array.size(); i++) {
		        int count = 0;
		       
		        for( int j = 0; j < array.size(); j++){
		          if (array.get(i)==array.get(j) )
		              count++;
		    }
		   if (count >= max)
		       max = count;
		   pos = array.get(i);
		  }
			 MaxAndPosition[0]= max;
			 MaxAndPosition[1]= pos;
			return MaxAndPosition;
		 
	 }
	
	
	@Override
	public void complaintsSystemDecision(Long IdM) {
		String toEmail = "";
		String Subject =" ";
		String body = "";
		Manager UserDecision= null ;
		Manager UserAdmin = managerrepository.findById(IdM).orElse(null);
		/*Customer UserDecision= null ;
		Customer UserAdmin = customerrepository.findById(IdM).orElse(null);*/

		
		//1:All Reclamations
		List<Complaint> AllReclamations = new ArrayList<Complaint>();
		AllReclamations = retrieveAllComplaints();

		
		List<Complaint> ReclamationsProfile = new ArrayList<Complaint>();
		ArrayList<Integer> RecProfileID = new ArrayList<Integer>();

		//2:Classer les rec selon leur sujet + les ID du profile
		for(Complaint r : AllReclamations) {			

			//Non Traiter  
			//r.getStatus().equals(false)
			if(r.getStatus() == false) {

				 if ( r.getComplaintSubject().equals(ComplaintSubject.ReportProfile) ) {
					ReclamationsProfile.add(r);

					
					RecProfileID.add(r.getNum());
					}
				}
		}
		//3:  max redundant element from list
		
		
		
		Integer[] MaxAndPositionProfile= null;
		
		if(! (RecProfileID.isEmpty()) ) {
			 MaxAndPositionProfile = MaxOccur(RecProfileID);}
		
		
		
		

		//2:Traiter ReportProfile
				if(MaxAndPositionProfile != null) {
					System.out.println("aaaaaaaaaaaaa");
					System.out.println(MaxAndPositionProfile[0]);
					System.out.println("/////////////////////");

					System.out.println(RecProfileID.get(MaxAndPositionProfile[1]));
					System.out.println("////////////////////////");


					if( MaxAndPositionProfile[0] > 2) {
						System.out.println("mmmkkkkkkkkkkkkkkkm");
						System.out.println(MaxAndPositionProfile[0]);

						System.out.println("tu dois effacer ce comment d'id: " + RecProfileID.get(MaxAndPositionProfile[1]));
						UserDecision = managerrepository.findById(Long.valueOf(RecProfileID.get(MaxAndPositionProfile[1]))).orElse(null);

						//UserDecision = customerrepository.findById(Long.valueOf(RecProfileID.get(MaxAndPositionProfile[1]))).orElse(null);
						System.out.println(UserDecision);

						System.out.println("mmmmmmm");

						if(UserDecision != null) {
							
							System.out.println("mail send successfully");
						    SimpleMailMessage message = new SimpleMailMessage();
							message.setFrom("farouk.chtioui@esprit.tn");
							message.setTo(/*UserDecision.getEmail()*/"farouk.chtioui@esprit.tn");
							message.setText("Hi Mr "+UserDecision.getLastName()+" ,we receive more than 2 Report to your Profile so we advice you to communicate with your collegeues trying to get a solution.");
							message.setSubject("Profile Complaint ");
							mailSender.send(message) ;
						}
						
						//Changer ces 6 reclamation en traité
						for(Complaint rp : ReclamationsProfile) {
							System.out.println("xxxxxxxxxxxxxxxy");
							rp.setStatus(true);
							System.out.println("xxxxxxxxxxkkkkkkkkkqqqq");
							complaintrepository.save(rp);
						}
					}
				}
	}
	
	


@Override
public float TreatedComplaint() {
	
	float s= (float)((float)complaintrepository.CountStatus(true)/complaintrepository.CountAllStatus())*100;
	System.out.println("ssssssssssssssssss"+s);
	return s;
}

@Override
public String GetType(Long id_c) {
	Complaint c = complaintrepository.findById(id_c).orElse(null);
	int d = 0;
	int p = 0;
	int a = 0;
	List<String> negative_key = new ArrayList<>();
	negative_key.add("connexion");negative_key.add("service");negative_key.add("erreur");
	
	List<String> appoint_key = new ArrayList<>();
	appoint_key.add("non disponible");appoint_key.add("calendrier");appoint_key.add("rendez vous manque");
	
	List<String> credit_key = new ArrayList<>();
	credit_key.add("credit rejete");credit_key.add("somme non desire");
	
	for (String string : negative_key) {
		if (c.getText().contains(string))
			d++;
		
	}
	for (String string : appoint_key) {
		if(c.getText().contains(string))
			p++;
			
	}
	for (String string : credit_key) {
		if(c.getText().contains(string))
			a++;
			
	}
	
	if (d>p && d>a) return "reclamation type technique";
	else if (a>d && a>p) return "reclamation type rendez vous";
	else return "reclamation type credit";
	
}



}
