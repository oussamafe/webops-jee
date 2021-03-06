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
    	
    //	workerBean.AutoDeleteInterview(); //not implemented yet // every year
    //	workerBean.AutoDeleteOnlineTest();//tested OK
    }
    
    
    /**
     * everyday
     */
    @Schedule(second = "0", minute = "0", hour = "0",dayOfMonth="*",dayOfWeek="*",month="*",year="*", persistent = false)
    public void EveryDayTasks() throws InterruptedException {
       
        //workerBean.AutoRefuseOnlineTest();
        //workerBean.AutoRemoveAvailability();
        //workerBean.InterviewPropertiesAlgo();
    	//workerBean.AddAvailabilityDayForAllEmp()
    }
    
    
    @Schedule(second = "0", minute = "0", hour = "0",dayOfMonth="1",month="1",year="*" ,persistent = false)
    public void EveryYearTasks() throws InterruptedException {
       
    	//workerBean.AutoDeleteInterview(); 
    	//workerBean.AutoDeleteOnlineTest();
    }
    
}
