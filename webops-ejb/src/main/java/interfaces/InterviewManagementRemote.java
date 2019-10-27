package interfaces;

import java.util.Date;
import java.util.Set;

import javax.ejb.Remote;

import entities.Interview;
import entities.InterviewType;

@Remote
public interface InterviewManagementRemote {
	public int AddInterview(Interview interview);
	public int AddInterviewType(InterviewType interviewType);
	
	public void UpdateInterview(int interviewID,Interview interview);
	public void UpdateInterviewType(int interviewTypeID,InterviewType interviewType);
	
	public Set<Interview> ListAllInterview();
	public Set<Interview> ListInterviewByCandidate(int candidateID);
	public Set<Interview> ListInterviewByEmploye(int employeID);
	public Set<Interview> ListInterviewPerDate(Date dateInterview);	
	public Set<Interview> ListInterviewByType(int InterviewTypeID);
	
	public Set<InterviewType> ListAllInterviewType();
	
	public void DeleteInterviewType(int interviewTypeID);
	public void DeleteInterview(int interviewID);
	
	public void AffectInterviewToEmploye(int interviewID,int employeID);
	public void AffectInterviewToCandidate(int interviewID,int candidateID);
	public void AffectInterviewTypeToInterview(int interviewID,int interviewTypeID);
	
	public void RemoveInterviewfromEmploye(int interviewID,int employeID);
	public void RemoveInterviewfromCandidate(int interviewID,int candidateID);
	public void RemoveInterviewfromInterviewType(int interviewID,int interviewTypeID); 
	
}
