package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entities.Application;
import interfaces.ApplicationInterfaceRemote;
@Stateless
@LocalBean
public class ApplicationService implements ApplicationInterfaceRemote {

	@Override
	public int addApplication(Application A, int idCandidate, int idJobOffer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void RemoveApplication(int idCandidate, int idJobOffer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Application displayApplicationDetails(int idCandidate, int idJobOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateApplication(int idCandidate, int idJobOffer) {
		// TODO Auto-generated method stub

	}

}
