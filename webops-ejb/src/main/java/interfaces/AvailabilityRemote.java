package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.Availability;
import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;


@Remote
public interface AvailabilityRemote {
	
	public void AddAvailabilityCandidate(int candidateID,AvailabilityCandidate availabilityCandidate);// tested OK
	public void AddAvailabilityEmploye(int employeID,AvailabilityEmploye availabilityCandidate);// tested OK
	public void DeleteAvailability(int availabilityID);//tested OK
	
	public Set<AvailabilityCandidate> ListAvailabilityCandidate(int candidateID);//tested OK
	public Set<AvailabilityEmploye> ListAvailabilityEmploye(int employeID);//tested OK
	
}
