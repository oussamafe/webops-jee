package interfaces;

import javax.ejb.Remote;

import entities.Application;
import entities.ApplicationId;

@Remote
public interface ApplicationInterfaceRemote {

	public void addApplication(Application A, int idCandidate, int idJobOffer);
	public void RemoveApplication(int idCandidate, int idJobOffer);
	public Application displayApplicationDetails(int idCandidate, int idJobOffer);
	public void updateApplication(int idCandidate, int idJobOffer);
	
	
}

