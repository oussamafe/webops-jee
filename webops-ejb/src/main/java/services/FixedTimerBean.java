package services;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class FixedTimerBean {
 
    @EJB
    private WorkerBean workerBean;
 
    
    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void EveryFiveSecondesTasks() throws InterruptedException {
     //   workerBean.doTimerWork();  
    //	 workerBean.AutoRefuseOnlineTest();   //tested OK
    //    workerBean.InterviewPropertiesAlgo();//tested OK
   // 	 workerBean.AutoRemoveAvailability();        //tested OK
   // 	workerBean.AddAvailabilityDayForAllEmp();    //tested OK
    	
    	//workerBean.autodeleteInterview(); //not implemented yet
    	//workerBean.autodeleteOnlinetest();//not implemented yet
    }
    
    
    /**
     * everyday
     */
    @Schedule(second = "1", minute = "1", hour = "*", persistent = false)
    public void EveryDayTasks() throws InterruptedException {
       
        //workerBean.AutoRefuseOnlineTest();
        //workerBean.AutoRemoveAvailability();
        //workerBean.InterviewPropertiesAlgo();
    	//workerBean.AddAvailabilityDayForAllEmp()
    }
    
    
}
