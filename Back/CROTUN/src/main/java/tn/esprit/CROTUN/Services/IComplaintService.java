package tn.esprit.CROTUN.Services;

import java.util.List;


import tn.esprit.CROTUN.Entities.Complaint;

public interface IComplaintService {

	List<Complaint> retrieveAllComplaints();

	Complaint addComplaint (Complaint c, Long IdM, Long IdC, Long IdA, int num , String complaintSubject);
	
	void deleteComplaint(Long id);

	Complaint updateComplaint (Complaint c, Long IdM, Long IdC, Long IdA,String complaintSubject,String text);


	Complaint retrieveComplaint(Long id);
	
	public Boolean badWordsValidation(String[] wordsFromText);
	
	void traiter(Long rec, String reponse);
	
	public Complaint createReclamation(Complaint c, Long IdM, Long IdC, Long IdA, int num,String complaintSubject );
	public void complaintsSystemDecision(Long IdC);

	float TreatedComplaint();
	public String GetType(Long id_c);


}
