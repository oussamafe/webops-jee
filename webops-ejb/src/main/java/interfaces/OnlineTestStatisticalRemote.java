package interfaces;

import javax.ejb.Remote;


@Remote
public interface OnlineTestStatisticalRemote {

	public int AcceptedTestPerYear(int year);//yet
	public int RejectedTestPerYear(int year);//yet
	public int AcceptedTestPerMonth(int year,int month);//yet
	public int RejectedTestPerMonth(int year,int month);//yet
	
	public int PercentOfExistanceQuestionInTests();
}
