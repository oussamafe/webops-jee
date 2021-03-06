package services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import services.AvailabilityImplementation;
import entities.Candidate;
import entities.Employe;
import entities.Interview;
import entities.InterviewType;
import entities.StateTestOnline;
import interfaces.InterviewManagementRemote;

@Stateless
@LocalBean
public class InterviewManagementImplimentation implements InterviewManagementRemote{
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	@Inject
	AvailabilityImplementation ai;
	@Override
	public int AddInterview(int candidateID) {	
		
		for(InterviewType it:ListAllInterviewType())
		{
			Interview i=new Interview();
			i.setState(StateTestOnline.WaitForIt);
			em.persist(i);			
			AffectInterviewTypeToInterview(i.getId(),it.getId());
			AffectInterviewToCandidate(i.getId(),2);
		}						
		return 1;
	}

	@Override
	public int AddInterviewType(InterviewType interviewType) {
		 em.persist(interviewType);
		return interviewType.getId();
	}

	@Override
	public void SetValidInterview(int interviewID ) {
		Interview i=em.find(Interview.class,interviewID );
		i.setState(StateTestOnline.Valid);
	}
	@Override
	public void SetInValidInterview(int interviewID ) {
		Interview i=em.find(Interview.class,interviewID );
		i.setState(StateTestOnline.InValid);
	}
	
	@Override
	public void UpdateInterview(int interviewID, Interview interview) {
		Interview i=em.find(Interview.class,interviewID );
		i.setDate(interview.getDate());
		i.setStartHour(interview.getStartHour());
		RemoveInterviewfromCandidate(interviewID, i.getCandidatInterview().getId());
		RemoveInterviewfromEmploye(interviewID, i.getEmployeInterview().getId());
		RemoveInterviewfromInterviewType(interviewID, i.getInterviewType().getId());
		
		AffectInterviewToCandidate(interviewID, interview.getCandidatInterview().getId());
		AffectInterviewToEmploye(interviewID, interview.getEmployeInterview().getId());
		AffectInterviewTypeToInterview(interviewID,interview.getInterviewType().getId());
		
	}

	@Override
	public void UpdateInterviewType(int interviewTypeID, InterviewType interviewType) {
		InterviewType it=em.find(InterviewType.class,interviewTypeID );		
		it.setHours_number(interviewType.getHours_number());
		it.setType(interviewType.getType());
		it.setRoleOfEmploye(interviewType.getRoleOfEmploye());
		
	}

	@Override
	public Set<Interview> ListAllInterview() {
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a ", Interview.class);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results;
	}

	
	
	
	
	
	@Override
	public Set<Interview> ListInterviewByCandidate(int candidateID) {
		Candidate c=em.find(Candidate.class,candidateID );	
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a where a.candidatInterview=:c", Interview.class);
		query.setParameter("c", c);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results;
	}

	
	
	
	
	
	
	
	@Override
	public Set<Interview> ListInterviewByEmploye(int employeID) {
		Employe e=em.find(Employe.class,employeID );
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a where a.employeInterview=:c", Interview.class);
		query.setParameter("c", e);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Interview> ListInterviewPerDate(Date dateInterview) {
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a where a.date=:c", Interview.class);
		query.setParameter("c", dateInterview);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Interview> ListInterviewByType(int InterviewTypeID) {
		InterviewType it=em.find(InterviewType.class,InterviewTypeID );
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a where a.interviewType=:c", Interview.class);
		query.setParameter("c", it);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<InterviewType> ListAllInterviewType() {
		TypedQuery<InterviewType> query = em.createQuery("SELECT a FROM InterviewType a ", InterviewType.class);
		Set<InterviewType> results = new HashSet<InterviewType>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public void DeleteInterviewType(int interviewTypeID) {
		InterviewType it=em.find(InterviewType.class,interviewTypeID );
		em.remove(it);
		
	}

	@Override
	public void DeleteInterview(int interviewID) {
		Interview i=em.find(Interview.class,interviewID );
		Candidate c=em.find(Candidate.class, i.getCandidatInterview().getId());		
		InterviewType it=em.find(InterviewType.class,i.getInterviewType().getId());
		
		
		try {
			Employe e=em.find(Employe.class, i.getEmployeInterview().getId());
			e.getInterviews().remove(i);
		}catch(NullPointerException ex)
		{
			System.out.println("null");
		}
		
		
		c.getInterviews().remove(i);		
		it.getInterviews().remove(i);
	
		em.remove(i);
		
	}

	
	@Override
	public void AffectInterviewToEmploye(int interviewID, int employeID) {
		Interview i=em.find(Interview.class,interviewID );
		Employe e=em.find(Employe.class,employeID );
		i.setEmployeInterview(e);
		e.getInterviews().add(i);
		
	}

	@Override
	public void AffectInterviewToCandidate(int interviewID, int candidateID) {
		Interview i=em.find(Interview.class,interviewID );
		Candidate c=em.find(Candidate.class,candidateID );
		i.setCandidatInterview(c);
		c.getInterviews().add(i);
		
	}

	@Override
	public void AffectInterviewTypeToInterview(int interviewID, int interviewTypeID) {
		Interview i=em.find(Interview.class,interviewID );
		InterviewType it=em.find(InterviewType.class,interviewTypeID );
		i.setInterviewType(it);
		it.getInterviews().add(i);
		
	}

	

	@Override
	public void RemoveInterviewfromEmploye(int interviewID,int employeID) {
		Interview i=em.find(Interview.class,interviewID );
		Employe e=em.find(Employe.class,employeID );
		e.getInterviews().remove(i);
		
	}

	@Override
	public void RemoveInterviewfromCandidate(int interviewID,int candidateID) {
		Interview i=em.find(Interview.class,interviewID );
		Candidate c=em.find(Candidate.class,candidateID );
		c.getInterviews().remove(i);
		
	}

	@Override
	public void RemoveInterviewfromInterviewType(int interviewID, int interviewTypeID) {
		Interview i=em.find(Interview.class,interviewID );
		InterviewType it=em.find(InterviewType.class,interviewTypeID );
		it.getInterviews().remove(i);
		
	}
	
	
	
}
