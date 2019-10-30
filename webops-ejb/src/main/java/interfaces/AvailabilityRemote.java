package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;


@Remote
public interface AvailabilityRemote {
	
	//public void AddAvailabilityCandidate(int candidateID,AvailabilityCandidate availabilityCandidate);// tested OK
	//public void AddAvailabilityEmploye(int employeID,AvailabilityEmploye availabilityCandidate);// tested OK
	//public void DeleteAvailability(int availabilityID);//tested OK
	/**
	 * 
	 * @param candidateID
	 * 
	 * initialise candidate availability by 10 available days 
	 * candidate can  change later state to any availability he want  to false as unavailable
	 */
	public void InitialiseCandidateAvailability(int candidateID);
	/**
	 * 
	 * @param EmployeID
	 * 
	 *  initialise Employe availability by 10 available days 
	 */
	public void InitialiseEmployeAvailability(int EmployeID);
	public Set<AvailabilityCandidate> ListAvailabilityCandidate(int candidateID);//tested OK
	public Set<AvailabilityEmploye> ListAvailabilityEmploye(int employeID);//tested OK
	
}
