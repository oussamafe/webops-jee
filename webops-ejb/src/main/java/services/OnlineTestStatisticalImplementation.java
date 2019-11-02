package services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.OnlineTest;
import entities.Question;
import entities.StateTestOnline;
import interfaces.OnlineTestStatisticalRemote;

@Stateless
@LocalBean
public class OnlineTestStatisticalImplementation implements OnlineTestStatisticalRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	OnlineTestImplementation OTI;

	@Override
	public float AcceptedTestPerYear(int year) {
		TypedQuery<OnlineTest> query = em.createQuery(
				"SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d and a.state=:s", OnlineTest.class);
		query.setParameter("d", year);
		query.setParameter("s", StateTestOnline.Valid);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbTestPerYear(year);

		return x * 100;
	}

	@Override
	public float RejectedTestPerYear(int year) {
		TypedQuery<OnlineTest> query = em.createQuery(
				"SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d and a.state=:s", OnlineTest.class);
		query.setParameter("d", year);
		query.setParameter("s", StateTestOnline.InValid);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbTestPerYear(year);
		return x * 100;
	}

	@Override
	public float AcceptedTestPerMonth(int year, int month) {
		TypedQuery<OnlineTest> query = em.createQuery(
				"SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m and a.state=:s",
				OnlineTest.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		query.setParameter("s", StateTestOnline.Valid);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());

		float x = (float) results.size() / NbTestPerMonth(year, month);
		return x * 100;
	}

	@Override
	public float RejectedTestPerMonth(int year, int month) {
		TypedQuery<OnlineTest> query = em.createQuery(
				"SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m and a.state=:s",
				OnlineTest.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		query.setParameter("s", StateTestOnline.InValid);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbTestPerMonth(year, month);
		return x * 100;
	}

	

	@Override
	public int NbQuestionPerModule(String Module) {
		return OTI.ListModuleOfQuestion().size();
	}

	@Override
	public int NbTestPerYear(int year) {
		TypedQuery<OnlineTest> query = em.createQuery("SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d",
				OnlineTest.class);
		query.setParameter("d", year);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());
		return results.size();
	}

	@Override
	public int NbTestPerMonth(int year, int month) {
		TypedQuery<OnlineTest> query = em.createQuery(
				"SELECT a FROM OnlineTest a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m",
				OnlineTest.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		Set<OnlineTest> results = new HashSet<OnlineTest>();
		results.addAll(query.getResultList());
		return results.size();
	}

}
