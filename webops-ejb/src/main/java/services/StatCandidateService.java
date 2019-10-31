package services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.StatCandidateRemote;
@Stateless
@LocalBean
public class StatCandidateService implements StatCandidateRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
//pas encore tester
	@Override
	public Long UserNbrVistAccountbyCandidate(int idCandidate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long NbrApplicationDonebyCandidate(int idCandidate) {
		TypedQuery<Long> query =
				em.createQuery("select count(A) from Application A where A.candidate="+idCandidate, Long.class);
		Long nbr=query.getSingleResult();
		return nbr;
	}

	@Override
	public Long NbrApplicationAcceptedbyCandidate(int idCandidate) {
		TypedQuery<Long> query =
				em.createQuery("select count(A) from Application A where A.candidate="+idCandidate+"and A.result=:results", Long.class);
		query.setParameter("results", 1);
		Long nbr=query.getSingleResult();
		return nbr;
	}

	@Override
	public Long NbrApplicationRefusedbyCandidate(int idCandidate) {
		TypedQuery<Long> query =em.createQuery
		("select count(A) from Application A where A.candidate="+idCandidate+" and  A.result=:results and A.answerDate is not NULL", Long.class);
		query.setParameter("results", 0);
		
		Long nbr=query.getSingleResult();
		return nbr;
	}

	@Override
	public Long nbrALlCandidate() {
		TypedQuery<Long> query = em.createQuery("select count(*) from Candidate", Long.class);
		Long nbr=query.getSingleResult();
		return nbr;
	}

	@Override
	public Long nbrApplicationAllSite() {

		TypedQuery<Long> query = em.createQuery("select count(*) from Application", Long.class);
		
	
		Long AllnbrApp=query.getSingleResult();
		System.out.println(AllnbrApp);
		return AllnbrApp;
	}

	@Override
	public Long nbrApplicationAllSitethisMonth() {
		Date date = new Date();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		TypedQuery<Long> query2 = em.createQuery("select count(*) from Application A where Month(A.depositDate)=Month(:date)", Long.class);
		query2.setParameter("date", formatter.format(date));
		
		Long nbrAppThismonthLong=query2.getSingleResult();
		System.out.println(nbrAppThismonthLong);
		System.out.println(formatter.format(date));
		return nbrAppThismonthLong;
	}


	@Override
	public Long NbrApplicationWaitingReplybyCandidate(int idCandidate) {
		TypedQuery<Long> query =em.createQuery
				("select count(A) from Application A where A.candidate="+idCandidate+" and  A.result=:results and A.answerDate is NULL", Long.class);
				query.setParameter("results", 0);
				
				Long nbr=query.getSingleResult();
				return nbr;
	}
	

	

}
