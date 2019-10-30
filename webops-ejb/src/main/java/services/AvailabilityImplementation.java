package services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
/*
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
*/
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

	@Override
	public void InitialiseCandidateAvailability(int candidateID) {
		Candidate e = em.find(Candidate.class, candidateID);
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		
		for(int x=0;x<10;x++)
		{						
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			LocalDate localDate = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			
			if(localDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)==0)
			{
				AvailabilityCandidate ac1=new AvailabilityCandidate();			
				AvailabilityCandidate ac2=new AvailabilityCandidate();
				AvailabilityCandidate ac3=new AvailabilityCandidate();
				ac1.setDate(dt);			ac1.setCandidate(e);			ac1.setStart_hour(8);			ac1.setEnd_hour(10);			ac1.setState(false);
				
				ac2.setDate(dt);			ac2.setCandidate(e);			ac2.setStart_hour(10);			ac2.setEnd_hour(12);			ac2.setState(false);
				
				ac3.setDate(dt);			ac3.setCandidate(e);			ac3.setStart_hour(2);			ac3.setEnd_hour(4);			ac3.setState(false);
				
				em.persist(ac1);			em.persist(ac2);			em.persist(ac3);
				
				e.getAvailabilityCandidate().add(ac1);	e.getAvailabilityCandidate().add(ac2);	e.getAvailabilityCandidate().add(ac3);
			}
			else
			{
				if(localDate.getDayOfWeek().compareTo(DayOfWeek.FRIDAY)==0  ||  localDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)==0)		
				{
					AvailabilityCandidate ac1=new AvailabilityCandidate();			
					AvailabilityCandidate ac2=new AvailabilityCandidate();				
					AvailabilityCandidate ac3=new AvailabilityCandidate();
					
					ac1.setDate(dt);			ac1.setCandidate(e);			ac1.setStart_hour(8);			ac1.setEnd_hour(10);			ac1.setState(true);
					
					ac2.setDate(dt);			ac2.setCandidate(e);			ac2.setStart_hour(10);			ac2.setEnd_hour(12);			ac2.setState(true);								
					
					ac3.setDate(dt);			ac3.setCandidate(e);			ac3.setStart_hour(2);			ac3.setEnd_hour(4);				ac3.setState(false);
					
					em.persist(ac1);			em.persist(ac2);			em.persist(ac3);
					
					e.getAvailabilityCandidate().add(ac1);	e.getAvailabilityCandidate().add(ac2);	e.getAvailabilityCandidate().add(ac3);
				}
				else
				{
					AvailabilityCandidate ac1=new AvailabilityCandidate();			
					AvailabilityCandidate ac2=new AvailabilityCandidate();
					AvailabilityCandidate ac3=new AvailabilityCandidate();
					
					ac1.setDate(dt);			ac1.setCandidate(e);			ac1.setStart_hour(8);			ac1.setEnd_hour(10);			ac1.setState(true);
					
					ac2.setDate(dt);			ac2.setCandidate(e);			ac2.setStart_hour(10);			ac2.setEnd_hour(12);			ac2.setState(true);
					
					ac3.setDate(dt);			ac3.setCandidate(e);			ac3.setStart_hour(2);			ac3.setEnd_hour(4);			ac3.setState(true);
					
					em.persist(ac1);			em.persist(ac2);			em.persist(ac3);
					
					e.getAvailabilityCandidate().add(ac1);	e.getAvailabilityCandidate().add(ac2);	e.getAvailabilityCandidate().add(ac3);
				
				}
			}
			
			
		}
	}

	@Override
	public void InitialiseEmployeAvailability(int EmployeID) {
		Employe e = em.find(Employe.class, EmployeID);
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		for(int x=0;x<10;x++)
		{						
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			LocalDate localDate = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			
			if(localDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)==0)
			{
				AvailabilityEmploye ae1=new AvailabilityEmploye();			
				AvailabilityEmploye ae2=new AvailabilityEmploye();
				AvailabilityEmploye ae3=new AvailabilityEmploye();
				ae1.setDate(dt);			ae1.setEmploye(e);			ae1.setStart_hour(8);			ae1.setEnd_hour(10);			ae1.setState(false);
				
				ae2.setDate(dt);			ae2.setEmploye(e);			ae2.setStart_hour(10);			ae2.setEnd_hour(12);			ae2.setState(false);
				
				ae3.setDate(dt);			ae3.setEmploye(e);			ae3.setStart_hour(2);			ae3.setEnd_hour(4);			ae3.setState(false);
				
				em.persist(ae1);			em.persist(ae2);			em.persist(ae3);
				
				e.getAvailabilityEmploye().add(ae1);	e.getAvailabilityEmploye().add(ae2);	e.getAvailabilityEmploye().add(ae3);
			}
			else
			{
				if(localDate.getDayOfWeek().compareTo(DayOfWeek.FRIDAY)==0  ||  localDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)==0)		
				{
					AvailabilityEmploye ae1=new AvailabilityEmploye();			
					AvailabilityEmploye ae2=new AvailabilityEmploye();				
					AvailabilityEmploye ae3=new AvailabilityEmploye();
					
					ae1.setDate(dt);			ae1.setEmploye(e);			ae1.setStart_hour(8);			ae1.setEnd_hour(10);			ae1.setState(true);
					
					ae2.setDate(dt);			ae2.setEmploye(e);			ae2.setStart_hour(10);			ae2.setEnd_hour(12);			ae2.setState(true);								
					
					ae3.setDate(dt);			ae3.setEmploye(e);			ae3.setStart_hour(2);			ae3.setEnd_hour(4);				ae3.setState(false);
					
					em.persist(ae1);			em.persist(ae2);			em.persist(ae3);
					
					e.getAvailabilityEmploye().add(ae1);	e.getAvailabilityEmploye().add(ae2);	e.getAvailabilityEmploye().add(ae3);
				}
				else
				{
					AvailabilityEmploye ae1=new AvailabilityEmploye();			
					AvailabilityEmploye ae2=new AvailabilityEmploye();
					AvailabilityEmploye ae3=new AvailabilityEmploye();
					
					ae1.setDate(dt);			ae1.setEmploye(e);			ae1.setStart_hour(8);			ae1.setEnd_hour(10);			ae1.setState(true);
					
					ae2.setDate(dt);			ae2.setEmploye(e);			ae2.setStart_hour(10);			ae2.setEnd_hour(12);			ae2.setState(true);
					
					ae3.setDate(dt);			ae3.setEmploye(e);			ae3.setStart_hour(2);			ae3.setEnd_hour(4);			ae3.setState(true);
					
					em.persist(ae1);			em.persist(ae2);			em.persist(ae3);
					
					e.getAvailabilityEmploye().add(ae1);	e.getAvailabilityEmploye().add(ae2);	e.getAvailabilityEmploye().add(ae3);
				
				}
			}
			
			
		}
		
		
	}

}
