package services;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Availability;
import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;
import entities.Candidate;
import entities.Employe;
import interfaces.AvailabilityRemote;

@Stateless
@LocalBean
public class AvailabilityImplementation implements AvailabilityRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public void AddAvailabilityCandidate(int candidateID, AvailabilityCandidate availabilityCandidate) {
		Candidate can = em.find(Candidate.class, candidateID);
		availabilityCandidate.setCandidate(can);
		can.getAvailabilityCandidate().add(availabilityCandidate);
	}

	@Override
	public void AddAvailabilityEmploye(int employeID, AvailabilityEmploye availabilityEmploye) {
		Employe emp = em.find(Employe.class, employeID);
		availabilityEmploye.setEmploye(emp);
		emp.getAvailabilityEmploye().add(availabilityEmploye);

	}

	@Override
	public void DeleteAvailability(int availabilityID) {
		Availability ava = em.find(Availability.class, availabilityID);
		em.remove(ava);
	}

	@Override
	public Set<AvailabilityCandidate> ListAvailabilityCandidate(int candidateID) {
		Candidate avaCan = em.find(Candidate.class, candidateID);
		TypedQuery<AvailabilityCandidate> query = em
				.createQuery("SELECT a FROM AvailabilityCandidate a where a.candidate=:m", AvailabilityCandidate.class);
		query.setParameter("m", avaCan);
		Set<AvailabilityCandidate> results = new HashSet<AvailabilityCandidate>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<AvailabilityEmploye> ListAvailabilityEmploye(int employeID) {
		Employe avaEmp = em.find(Employe.class, employeID);
		TypedQuery<AvailabilityEmploye> query = em.createQuery("SELECT a FROM AvailabilityEmploye a where a.employe=:m",
				AvailabilityEmploye.class);
		query.setParameter("m", avaEmp);
		Set<AvailabilityEmploye> results = new HashSet<AvailabilityEmploye>();
		results.addAll(query.getResultList());
		return results;

	}

}
