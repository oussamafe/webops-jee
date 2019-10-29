package services;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Interview;
import entities.StateTestOnline;

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
				"SELECT a FROM Interview a where (a.date IS NULL) AND (SELECT DATEDIFF(NOW(),x.date) FROM OnlineTest x WHERE x.candidatTest = a.candidatInterview )>2",
				Interview.class);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		//results have the full list of interview that  should  affect a date and employer to proceed the interview 
		//by the way these 2 days are made to give enough time to complete his unavailability date 

		//now we will search for the closer free date from the two side the candidate side and the employer side 
		//of course we taking in consideration  the role of employer that we searching for 
		
		for (Interview i : results) {
			System.out.println("id of interview : " + i.getId());
		}
	}

	/**
	 * if delay of doing the online test is done will auto refuse this task will run
	 * everyday
	 */
	public void AutoRefuseOnlineTest() throws InterruptedException {

		Query query = em.createQuery("UPDATE OnlineTest o SET o.state=:s WHERE o.date +3 < NOW()");
		query.setParameter("s", StateTestOnline.InValid);
		query.executeUpdate();
		System.out.println("AutoRefuseOnlineTest complite job for today ");
	}

}
