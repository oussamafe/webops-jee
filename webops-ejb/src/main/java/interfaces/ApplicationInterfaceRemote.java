package interfaces;

import javax.ejb.Remote;

import entities.Application;

@Remote
public interface ApplicationInterfaceRemote {

	public int addApplication(Application A, int idCandidate, int idJobOffer);
	public void RemoveApplication(int idCandidate, int idJobOffer);
	public Application displayApplicationDetails(int idCandidate, int idJobOffer);
	public void updateApplication(int idCandidate, int idJobOffer);
	
	
}

