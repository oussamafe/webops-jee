package interfaces;

import javax.ejb.Remote;

@Remote
public interface InterviewStatisticalRemote {

	
	public int NbInterviewPerYear(int year);//yet
	public int NbInterviewPerMonth(int year,int month);//yet
	
	public float AcceptedInterviewPerYear(int year);//yet
	public float RejectedInterviewPerYear(int year);//yet
	public float AcceptedInterviewPerMonth(int year,int month);//yet
	public float RejectedInterviewPerMonth(int year,int month);//yet
	
}
