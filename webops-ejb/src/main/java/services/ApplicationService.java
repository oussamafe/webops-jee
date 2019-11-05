package services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import entities.Application;
import entities.ApplicationId;

import interfaces.ApplicationInterfaceRemote;
@Stateless
@LocalBean
public class ApplicationService implements ApplicationInterfaceRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	@Override
	public void addApplication(Application A, int idCandidate, int idJobOffer) 
	{	Date date = new Date();
		ApplicationId PK = new ApplicationId();
		PK.setIdCandidate(idCandidate);
		PK.setIdJobOffer(idJobOffer);
		A.setId(PK);
		A.setDepositDate(date);
		em.persist(A);
	
	}

	@Override
	public void RemoveApplication(int idC, int idJ) {
		int query=
					em.createQuery("Delete from Application A where A.candidate="+idC+" and A.jobOffer="+idJ )
					  .executeUpdate();
		
	}

	@Override
	public Application displayApplicationDetails(int idCandidate, int idJobOffer) {
		ApplicationId PK = new ApplicationId();
		PK.setIdCandidate(idCandidate);
		PK.setIdJobOffer(idJobOffer);
		Application A = em.find(Application.class, PK);
		//System.out.print(A);
		return A;
	}
//A discuter
	@Override
	public void updateApplication(int idCandidate, int idJobOffer) {
		
	}

}
