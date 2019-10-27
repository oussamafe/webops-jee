package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;


@Remote
public interface AvailabilityRemote {
	
	public void AddAvailabilityCandidate(int candidateID,AvailabilityCandidate availabilityCandidate);
	public void AddAvailabilityEmploye(int employeID,AvailabilityEmploye availabilityCandidate);
	public void DeleteAvailability(int availabilityID);
	
	
	public Set<AvailabilityCandidate> ListAvailabilityCandidate(int candidateID);
	public Set<AvailabilityEmploye> ListAvailabilityEmploye(int employeID);
	
}
