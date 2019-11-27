package interfaces;

import java.util.Date;
import java.util.Set;

import javax.ejb.Remote;

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;


@Remote
public interface AvailabilityRemote {
	
	
	
	public void SetNoAvailibleCandidate(int candidateID,Date dateav,int heurDebut);//tested OK
	public void SetAvailibleCandidate(int candidateID,Date dateav,int heurDebut);//tested OK
	public void SetNoAvailibleEmploye(int EmployeID,Date dateav,int heurDebut);//tested OK
	public void SetAvailibleEmploye(int EmployeID,Date dateav,int heurDebut);//tested OK
	/**
	 * 
	 * @param candidateID
	 * 
	 * initialise candidate availability by 10 available days 
	 * candidate can  change later state to any availability he want  to false as unavailable
	 */
	public void InitialiseCandidateAvailability(int candidateID);//tested OK
	/**
	 * 
	 * @param EmployeID
	 * 
	 *  initialise Employe availability by 10 available days 
	 */
	public void InitialiseEmployeAvailability(int EmployeID);//tested OK
	public Set<AvailabilityCandidate> ListAvailabilityCandidate(int candidateID);//tested OK
	public Set<AvailabilityEmploye> ListAvailabilityEmploye(int employeID);//tested OK
	
}
