package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Application;
import interfaces.ApplicationCandidateManagementRemote;

@Stateless
@LocalBean
public class ApplicationCandidateManagementImplementation implements ApplicationCandidateManagementRemote{

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	public ApplicationCandidateManagementImplementation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Application> ViewAllApplicationStillWait() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Application> ViewAllApplicationAccepted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Application> ViewAllApplicationRejected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean AcceptApplication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RejectApplication() {
		// TODO Auto-generated method stub
		return false;
	}
}
