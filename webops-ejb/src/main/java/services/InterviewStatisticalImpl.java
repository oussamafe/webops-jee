package services;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Interview;
import entities.OnlineTest;
import entities.StateTestOnline;
import interfaces.InterviewStatisticalRemote;

@Stateless
@LocalBean
public class InterviewStatisticalImpl implements InterviewStatisticalRemote {

	
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int NbInterviewPerYear(int year) {
		TypedQuery<Interview> query = em.createQuery("SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d",
				Interview.class);
		query.setParameter("d", year);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results.size();
	}

	@Override
	public int NbInterviewPerMonth(int year, int month) {
		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m",
				Interview.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		return results.size();
	}

	@Override
	public float AcceptedInterviewPerYear(int year) {
		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d and a.state=:s", Interview.class);
		query.setParameter("d", year);
		query.setParameter("s", StateTestOnline.Valid);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbInterviewPerYear(year);

		return x * 100;
	}

	@Override
	public float RejectedInterviewPerYear(int year) {
		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d and a.state=:s", Interview.class);
		query.setParameter("d", year);
		query.setParameter("s", StateTestOnline.InValid);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbInterviewPerYear(year);
		return x * 100;
	}

	@Override
	public float AcceptedInterviewPerMonth(int year, int month) {
		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m and a.state=:s",
				Interview.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		query.setParameter("s", StateTestOnline.Valid);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());

		float x = (float) results.size() / NbInterviewPerMonth(year, month);
		return x * 100;
	}

	@Override
	public float RejectedInterviewPerMonth(int year, int month) {
		TypedQuery<Interview> query = em.createQuery(
				"SELECT a FROM Interview a where EXTRACT(YEAR FROM a.date)=:d and EXTRACT(MONTH FROM a.date)=:m and a.state=:s",
				Interview.class);
		query.setParameter("d", year);
		query.setParameter("m", month);
		query.setParameter("s", StateTestOnline.InValid);
		Set<Interview> results = new HashSet<Interview>();
		results.addAll(query.getResultList());
		float x = (float) results.size() / NbInterviewPerMonth(year, month);
		return x * 100;
	}
	
	
}
