package interfaces;

import javax.ejb.Remote;


@Remote
public interface OnlineTestStatisticalRemote {

	public int NbTestPerYear(int year);//yet
	public int NbTestPerMonth(int year,int month);//yet
	
	public float AcceptedTestPerYear(int year);//yet
	public float RejectedTestPerYear(int year);//yet
	public float AcceptedTestPerMonth(int year,int month);//yet
	public float RejectedTestPerMonth(int year,int month);//yet
	
	public float PercentOfExistanceQuestionInTests();
}
