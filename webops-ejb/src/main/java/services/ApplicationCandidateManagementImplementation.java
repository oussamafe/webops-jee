package services;

import java.util.HashSet;
import java.util.List;
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
public class ApplicationCandidateManagementImplementation implements ApplicationCandidateManagementRemote{

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	public ApplicationCandidateManagementImplementation() {
		// TODO Auto-generated constructor stub
	}

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
