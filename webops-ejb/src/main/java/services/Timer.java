package services;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
@Singleton
public class Timer {
	 	@EJB
	    private WorkMail workerBean;

	 @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	    public void EveryFiveSecondesTasks() throws InterruptedException, MessagingException {
		// workerBean.GetAllJobOffersByWeek(	);
	 }

}
