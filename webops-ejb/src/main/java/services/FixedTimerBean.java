package services;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class FixedTimerBean {
 
    @EJB
    private WorkerBean workerBean;
 
    
    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void EveryFiveSecondesTasks() throws InterruptedException {
        workerBean.doTimerWork();       
        workerBean.InterviewPropertiesAlgo();
    }
    
    
    /**
     * everyday
     */
    @Schedule(second = "1", minute = "1", hour = "*", persistent = false)
    public void EveryDayTasks() throws InterruptedException {
       
        workerBean.AutoRefuseOnlineTest();
       
    }
    
    
}
