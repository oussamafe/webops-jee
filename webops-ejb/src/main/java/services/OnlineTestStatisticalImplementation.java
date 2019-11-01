package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.OnlineTestStatisticalRemote;

@Stateless
@LocalBean
public class OnlineTestStatisticalImplementation implements OnlineTestStatisticalRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	@Override
	public int AcceptedTestPerYear(int year) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int RejectedTestPerYear(int year) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int AcceptedTestPerMonth(int year, int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int RejectedTestPerMonth(int year, int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int PercentOfExistanceQuestionInTests() {
		// TODO Auto-generated method stub
		return 0;
	}

}
