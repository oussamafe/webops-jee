package services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;
import entities.Candidate;
import entities.Employe;
import entities.Interview;
import entities.InterviewType;
import entities.OnlineTest;
import entities.Role;
import entities.StateTestOnline;
import utilities.MailClass;

@Singleton
public class WorkerBean {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	int i = 0;

	public void doTimerWork() throws InterruptedException {
		i++;
		System.out.println("hi again " + i);
	}

	public void InterviewPropertiesAlgo() throws InterruptedException {

		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where (a.date IS NULL) AND (SELECT DATEDIFF(NOW(),x.date) FROM OnlineTest x WHERE x.candidatTest = a.candidatInterview  and x.state=:sss)>2",
				Interview.class);
		query.setParameter("sss", StateTestOnline.Valid);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		// results have the full list of interview that should affect a date and
		// employer to proceed the interview
		// by the way these 2 days are made to give enough time to complete his
		// unavailability date

		// now we will search for the closer free date from the two side the candidate
		// side and the employer side
		// of course we taking in consideration the role of employer that we searching
		// for

		for (Interview i : results) {

			Candidate c = em.find(Candidate.class, i.getCandidatInterview().getId());

			Set<AvailabilityCandidate> avaCan = GetCandidateAvailability(c);
			Set<Employe> emps = GetEmployes(i.getInterviewType().getRoleOfEmploye());
			if (emps.size() == 0) {
				System.out.println("no employe found  send mail  to add one ");
				TypedQuery<Employe> ad = em.createQuery("SELECT a FROM Employe a where a.role=:r", Employe.class);
				ad.setParameter("r", Role.Administrator);
				query.setFirstResult(0);
				query.setMaxResults(1);
				Employe admin = ad.getSingleResult(); 	
				String desc="after trying to affect and an interview { id =["+i.getId()+"] } with type "+i.getInterviewType().getType()+"  for candidate id=["+i.getCandidatInterview().getId()+"] \n "
						+ "named by \n FirstName : "+i.getCandidatInterview().getFirst_Name()+"\nLastName  "+i.getCandidatInterview().getLast_Name()+"\n"
								+ "we have problem that  there is no employe with role "+i.getInterviewType().getRoleOfEmploye()+" exist "+" ";
				new MailClass(admin.getEmail(), "Auto_Affecting_Interview", desc);
				
			} else {
				if (avaCan.size() == 0) {// if candiate have no avaibility  will affect by employer avibility
					System.out.println("use this  2 ");
					if (AffectInterviewByEmployeAvailability(emps, i) == false) {//if all employer have no availability so  add avai and affect  for random employe
						System.out.println("use this 3");
						List<Employe> l = new ArrayList<Employe>();
						l.addAll(emps);
						AddAvaibilityAndAffect(i, l.get(0));
					}
				} else {//search for match and affect
					if (AffectAnInterview(avaCan, emps, i) == false) { // if no match so will affect by employe avibility
						if (AffectInterviewByEmployeAvailability(emps, i) == false) {//if all employer have no availability so  add avai and affect  for random employe
							List<Employe> l = new ArrayList<Employe>();
							l.addAll(emps);
							AddAvaibilityAndAffect(i, l.get(0));
						}
					}
				}
			}
		}
		System.out.println("InterviewPropertiesAlgo complite job for today ");
	}

	/**
	 * search for the first matching in avaibility between employe and candidate
	 * 
	 * @return true if interview affected false if no matching found
	 */

	private boolean AffectAnInterview(Set<AvailabilityCandidate> avaCan, Set<Employe> emps, Interview i) {

		for (Employe e : emps) {
			Set<AvailabilityEmploye> avaemp = GetEmployeAvailability(e);
			if (avaemp.size() > 0) {
				for (AvailabilityCandidate ac : avaCan) {
					for (AvailabilityEmploye ae : avaemp) {
						if (ac.getDate().getTime() == ae.getDate().getTime() && ac.getStart_hour() == ae.getStart_hour()
								&& ac.getEnd_hour() == ae.getEnd_hour()) {
							Affect(e, ae, i);
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param e         employer that will be affected
	 * @param date      date of interview
	 * @param interview
	 * 
	 *                  this methode will affect an interview
	 */

	private void Affect(Employe e, AvailabilityEmploye ae, Interview i) {
		Interview ii = em.find(Interview.class, i.getId());
		Employe ee = em.find(Employe.class, e.getId());
		Candidate cc = em.find(Candidate.class, i.getCandidatInterview().getId());
		ii.setDate(ae.getDate());
		ii.setStartHour(ae.getStart_hour());
		ii.setEmployeInterview(e);
		ee.getInterviews().add(ii);

		// change avaibility state to false
		Query query = em
				.createQuery("UPDATE AvailabilityCandidate o SET o.state=:s WHERE o.date=:d and o.candidate=:c");
		query.setParameter("s", false);
		query.setParameter("d", ae.getDate());
		query.setParameter("c", cc);
		query.executeUpdate();

		Query query1 = em.createQuery(
				"UPDATE AvailabilityEmploye o SET o.state=:s WHERE o.date=:d and o.employe=:e and o.start_hour=:sh");
		query1.setParameter("s", false);
		query1.setParameter("d", ae.getDate());
		query1.setParameter("e", ee);
		query1.setParameter("sh", ae.getStart_hour());
		query1.executeUpdate();
	}

	/**
	 * Search for the first availability to any employe and affect interview on it
	 * 
	 * @return true if interview affected and false if no avaibility found
	 */

	private boolean AffectInterviewByEmployeAvailability(Set<Employe> emps, Interview i) {

		for (Employe e : emps) {
			Set<AvailabilityEmploye> avaemp = GetEmployeAvailability(e);
			if (avaemp.size() > 0) {
				for (AvailabilityEmploye ae : avaemp) {
					if (ae.getState() == true) {
						Affect(e, ae, i);
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * add an other available day and effect interview on it
	 */

	private void AddAvaibilityAndAffect(Interview i, Employe e) {
		int id = AddNewEmpAvailability(e);
		AvailabilityEmploye ae = em.find(AvailabilityEmploye.class, id);
		Affect(e, ae, i);

	}

	private Set<Employe> GetEmployes(Role roleOfEmploye) {
		TypedQuery<Employe> emps = em.createQuery("SELECT a FROM Employe a where a.role=:r", Employe.class);
		emps.setParameter("r", roleOfEmploye);
		Set<Employe> employes = new HashSet<Employe>();
		employes.addAll(emps.getResultList());
		return employes;
	}

	private Set<AvailabilityCandidate> GetCandidateAvailability(Candidate c) {
		TypedQuery<AvailabilityCandidate> ac = em.createQuery(
				"SELECT a FROM AvailabilityCandidate a where a.candidate=:r and a.state=:s",
				AvailabilityCandidate.class);
		ac.setParameter("r", c);
		ac.setParameter("s", true);
		Set<AvailabilityCandidate> ava = new HashSet<AvailabilityCandidate>();
		
		ava.addAll(ac.getResultList());		
		
		return ava;
	}

	private Set<AvailabilityEmploye> GetEmployeAvailability(Employe e) {
		TypedQuery<AvailabilityEmploye> ac = em.createQuery(
				"SELECT a FROM AvailabilityEmploye a where a.employe=:r and a.state=:s", AvailabilityEmploye.class);
		ac.setParameter("r", e);
		ac.setParameter("s", true);
		Set<AvailabilityEmploye> ava = new HashSet<AvailabilityEmploye>();
		ava.addAll(ac.getResultList());
		return ava;
	}

	/**
	 * if delay of doing the online test is done will auto refuse this task will run
	 * everyday
	 */
	public void AutoRefuseOnlineTest() throws InterruptedException {

		Query query = em.createQuery("UPDATE OnlineTest o SET o.state=:s WHERE DATEDIFF(NOW(),o.date) >3");
		query.setParameter("s", StateTestOnline.InValid);
		query.executeUpdate();
		System.out.println("AutoRefuseOnlineTest complite job for today ");
	}

	/**
	 * remove employe and candidate availability of last day
	 * 
	 * 
	 */
	public void AutoRemoveAvailability() {
		TypedQuery<AvailabilityEmploye> ae = em.createQuery("SELECT a FROM AvailabilityEmploye a where  a.date < NOW()",
				AvailabilityEmploye.class);
		Set<AvailabilityEmploye> ave = new HashSet<AvailabilityEmploye>();
		ave.addAll(ae.getResultList());

		TypedQuery<AvailabilityCandidate> ac = em.createQuery(
				"SELECT a FROM AvailabilityCandidate a where  a.date < NOW()", AvailabilityCandidate.class);
		Set<AvailabilityCandidate> avc = new HashSet<AvailabilityCandidate>();
		avc.addAll(ac.getResultList());

		for (AvailabilityEmploye e : ave) {
			em.remove(e);
		}
		for (AvailabilityCandidate c : avc) {
			em.remove(c);
		}

	}

	/**
	 * add an availability day for all employe
	 */

	public void AddAvailabilityDayForAllEmp() {
		TypedQuery<Employe> emps = em.createQuery("SELECT a FROM Employe a ", Employe.class);
		Set<Employe> employes = new HashSet<Employe>();
		employes.addAll(emps.getResultList());

		for (Employe e : employes) {
			AddNewEmpAvailability(e);
		}
	}

	/**
	 * add availability day for and specific employe
	 * 
	 * @param employer
	 * @return id of an availability if need it
	 */
	private int AddNewEmpAvailability(Employe e) {
		Employe ee = em.find(Employe.class, e.getId());
		TypedQuery<AvailabilityEmploye> query = em.createQuery(
				"SELECT a FROM AvailabilityEmploye a where a.employe=:r ORDER BY a.date DESC ",
				AvailabilityEmploye.class);
		query.setParameter("r", e);
		query.setFirstResult(0);
		query.setMaxResults(1);
		AvailabilityEmploye ave = query.getSingleResult();
		Date dt = ave.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		LocalDate localDate = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (localDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
			AvailabilityEmploye ae1 = new AvailabilityEmploye();
			AvailabilityEmploye ae2 = new AvailabilityEmploye();
			AvailabilityEmploye ae3 = new AvailabilityEmploye();

			ae1.setDate(dt);
			ae1.setEmploye(ee);
			ae1.setStart_hour(8);
			ae1.setEnd_hour(10);
			ae1.setState(false);

			ae2.setDate(dt);
			ae2.setEmploye(ee);
			ae2.setStart_hour(10);
			ae2.setEnd_hour(12);
			ae2.setState(false);

			ae3.setDate(dt);
			ae3.setEmploye(ee);
			ae3.setStart_hour(2);
			ae3.setEnd_hour(4);
			ae3.setState(false);
			em.persist(ae1);
			em.persist(ae2);
			em.persist(ae3);

			ee.getAvailabilityEmploye().add(ae1);
			ee.getAvailabilityEmploye().add(ae2);
			ee.getAvailabilityEmploye().add(ae3);

			Date dt1 = ae1.getDate();
			Calendar calen = Calendar.getInstance();
			calen.setTime(dt1);
			calen.add(Calendar.DATE, 1);
			dt1 = calen.getTime();

			AvailabilityEmploye ae11 = new AvailabilityEmploye();
			AvailabilityEmploye ae22 = new AvailabilityEmploye();
			AvailabilityEmploye ae33 = new AvailabilityEmploye();

			ae11.setDate(dt1);
			ae11.setEmploye(ee);
			ae11.setStart_hour(8);
			ae11.setEnd_hour(10);
			ae11.setState(true);

			ae22.setDate(dt1);
			ae22.setEmploye(ee);
			ae22.setStart_hour(10);
			ae22.setEnd_hour(12);
			ae22.setState(true);

			ae33.setDate(dt1);
			ae33.setEmploye(ee);
			ae33.setStart_hour(2);
			ae33.setEnd_hour(4);
			ae33.setState(true);

			em.persist(ae11);
			em.persist(ae22);
			em.persist(ae33);
			
			ee.getAvailabilityEmploye().add(ae11);
			ee.getAvailabilityEmploye().add(ae22);
			ee.getAvailabilityEmploye().add(ae33);
			return ae11.getId();
		} else {
			if (localDate.getDayOfWeek().compareTo(DayOfWeek.FRIDAY) == 0
					|| localDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0) {
				AvailabilityEmploye ae1 = new AvailabilityEmploye();
				AvailabilityEmploye ae2 = new AvailabilityEmploye();
				AvailabilityEmploye ae3 = new AvailabilityEmploye();

				ae1.setDate(dt);
				ae1.setEmploye(ee);
				ae1.setStart_hour(8);
				ae1.setEnd_hour(10);
				ae1.setState(true);

				ae2.setDate(dt);
				ae2.setEmploye(ee);
				ae2.setStart_hour(10);
				ae2.setEnd_hour(12);
				ae2.setState(true);

				ae3.setDate(dt);
				ae3.setEmploye(ee);
				ae3.setStart_hour(2);
				ae3.setEnd_hour(4);
				ae3.setState(false);

				em.persist(ae1);
				em.persist(ae2);
				em.persist(ae3);

				ee.getAvailabilityEmploye().add(ae1);
				ee.getAvailabilityEmploye().add(ae2);
				ee.getAvailabilityEmploye().add(ae3);
				return ae1.getId();
			} else {
				AvailabilityEmploye ae1 = new AvailabilityEmploye();
				AvailabilityEmploye ae2 = new AvailabilityEmploye();
				AvailabilityEmploye ae3 = new AvailabilityEmploye();

				ae1.setDate(dt);
				ae1.setEmploye(ee);
				ae1.setStart_hour(8);
				ae1.setEnd_hour(10);
				ae1.setState(true);

				ae2.setDate(dt);
				ae2.setEmploye(ee);
				ae2.setStart_hour(10);
				ae2.setEnd_hour(12);
				ae2.setState(true);

				ae3.setDate(dt);
				ae3.setEmploye(ee);
				ae3.setStart_hour(2);
				ae3.setEnd_hour(4);
				ae3.setState(true);

				em.persist(ae1);
				em.persist(ae2);
				em.persist(ae3);

				ee.getAvailabilityEmploye().add(ae1);
				ee.getAvailabilityEmploye().add(ae2);
				ee.getAvailabilityEmploye().add(ae3);
				return ae1.getId();
				
			}
			
		}
	}

	
	public void AutoDeleteInterview()
	{
		TypedQuery<Interview> interview = em.createQuery("SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)< EXTRACT(YEAR FROM NOW())-1", Interview.class);		
		Set<Interview> interviews = new HashSet<Interview>();
		interviews.addAll(interview.getResultList());
		
		for(Interview i:interviews)
		{
			DeleteInterview(i.getId());
		}
		
	}
	private void DeleteInterview(int interviewID) {
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
	public void AutoDeleteOnlineTest()
	{
		TypedQuery<OnlineTest> onlineTest = em.createQuery("SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)< EXTRACT(YEAR FROM NOW())-1", OnlineTest.class);		
		Set<OnlineTest> onlineTests = new HashSet<OnlineTest>();
		onlineTests.addAll(onlineTest.getResultList());
		
		for(OnlineTest i:onlineTests)
		{
			em.remove(i);
		}
		
	}
}
