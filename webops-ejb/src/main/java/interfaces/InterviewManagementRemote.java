package interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Interview;
import entities.InterviewType;

@Remote
public interface InterviewManagementRemote {
	public int AddInterview(int candidateID);//tested OK
	public int AddInterviewType(InterviewType interviewType);//tested OK	
	
	public void UpdateInterview(int interviewID,Interview interview);//tested OK
	public void UpdateInterviewType(int interviewTypeID,InterviewType interviewType);//tested OK
	
	public Set<Interview> ListAllInterview();//tested OK
	public Set<Interview> ListInterviewByCandidate(int candidateID);//tested OK
	public Set<Interview> ListInterviewByEmploye(int employeID);//tested OK
	public Set<Interview> ListInterviewPerDate(Date dateInterview);	//tested OK
	public Set<Interview> ListInterviewByType(int InterviewTypeID);//tested OK
	
	public Set<InterviewType> ListAllInterviewType();//tested OK
	
	public void DeleteInterviewType(int interviewTypeID);//tested OK
	public void DeleteInterview(int interviewID);//tested OK
	
	public void AffectInterviewToEmploye(int interviewID,int employeID);//tested OK
	public void AffectInterviewToCandidate(int interviewID,int candidateID);//tested OK
	public void AffectInterviewTypeToInterview(int interviewID,int interviewTypeID);//tested OK
	
	public void RemoveInterviewfromEmploye(int interviewID,int employeID);//tested OK
	public void RemoveInterviewfromCandidate(int interviewID,int candidateID);//tested OK
	public void RemoveInterviewfromInterviewType(int interviewID,int interviewTypeID); //tested OK
	
}
