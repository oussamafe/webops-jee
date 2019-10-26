package services;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Application;
import entities.ApplicationId;
import interfaces.ApplicationCandidateManagementRemote;

@Stateless
@LocalBean
public class ApplicationCandidateManagementImplementation implements ApplicationCandidateManagementRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	public ApplicationCandidateManagementImplementation() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public void ajouterApplication(int idJobOffer, int idCandiate, Date
	 * depositDate, Date answerDate) { ApplicationId ai=new ApplicationId();
	 * ai.setIdCandiate(idCandiate); ai.setIdJobOffer(idJobOffer);
	 * System.out.println("ai.getIdCandiate()    "+ai.getIdCandiate());
	 * System.out.println("problem here 10000000"); Candidate
	 * can=em.find(Candidate.class, idCandiate);
	 * System.out.println("problem here 1222222222"); JobOffer
	 * job=em.find(JobOffer.class, idJobOffer);
	 * System.out.println("problem here 1333333333");
	 * System.out.println("problem here 14444444");
	 * 
	 * Application a= new Application(); a.setId(ai); //a.setCandidate(can);
	 * //a.setJobOffer(job); a.setDepositDate(depositDate);
	 * a.setAnswerDate(answerDate); a.setResult(false);
	 * 
	 * System.out.println("problem here 1");
	 * System.out.println("a.getCandidate().getId()     "+a.getId().getIdCandiate()
	 * +"a.getAnswerDate()    " +a.getAnswerDate());
	 * System.out.println("problem here 2"); 
	 * em.persist(a);
	 * 
	 * 
	 * }
	 */
	@Override
	public Set<Application> ViewAllApplicationStillWait() {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a ", Application.class);
		Set<Application> results = new HashSet<Application>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Application> ViewAllApplicationAccepted() {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a where a.result=1",
				Application.class);
		Set<Application> results = new HashSet<Application>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Application> ViewAllApplicationRejected() {
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a where a.result=0",
				Application.class);
		Set<Application> results = new HashSet<Application>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public int AcceptApplication(ApplicationId id) {
		Query query = em.createQuery("update Application e set e.result=:r where e.id=:i");
		query.setParameter("r", true);
		query.setParameter("i", id);
		return query.executeUpdate();
	}

	@Override
	public int RejectApplication(ApplicationId id) {
		Query query = em.createQuery("update Application e set e.result=:r where e.id=:i");
		query.setParameter("r", false);
		query.setParameter("i", id);
		return query.executeUpdate();
	}
}
