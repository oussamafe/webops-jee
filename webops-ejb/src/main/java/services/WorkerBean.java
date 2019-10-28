package services;


import java.util.HashSet;
import java.util.Set;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.InterviewType;
import entities.StateTestOnline;
import interfaces.Schedule_AutoCall;

@Singleton
public class WorkerBean {
 
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
		int i=0;
    
    public void doTimerWork() throws InterruptedException {
    	i++;
        System.out.println("hi again "+i);
    }
    
    public void InterviewPropertiesAlgo()throws InterruptedException {
		
		System.out.println("hi again "+i);
		
	}
    
    /**
     * if delay of doing the online test is done will auto refuse 
     * this task will run everyday
     */
	public void AutoRefuseOnlineTest()throws InterruptedException {
		Query query = em.createQuery("UPDATE OnlineTest o SET o.state=:s WHERE o.date +3 < NOW()");
		query.setParameter("s", StateTestOnline.InValid);
		query.executeUpdate();
		System.out.println("AutoRefuseOnlineTest complite job for today ");		
	}

	
    
    
    
     
    
}
