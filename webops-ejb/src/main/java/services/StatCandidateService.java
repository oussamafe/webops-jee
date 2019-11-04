package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Candidate;
import entities.JobOffer;
import entities.Skill;
import interfaces.StatCandidateRemote;
@Stateless
@LocalBean
public class StatCandidateService implements StatCandidateRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public Long NbrApplicationDonebyCandidate(int idCandidate) {
		TypedQuery<Long> query =
				em.createQuery("select count(A) from Application A where A.candidate="+idCandidate, Long.class);
		Long nbr=query.getSingleResult();
		System.out.println(nbr);
		return nbr;
	}

	@Override
	public Long NbrApplicationAcceptedbyCandidate(int idCandidate) {
		TypedQuery<Long> query =
				em.createQuery("select count(A) from Application A where A.candidate="+idCandidate+"and A.result=:results", Long.class);
		query.setParameter("results",true);
		Long nbr=query.getSingleResult();
		System.out.println(nbr);
		return nbr;
	}

	@Override
	public Long NbrApplicationRefusedbyCandidate(int idCandidate) {
		TypedQuery<Long> query =em.createQuery
				("select count(A) from Application A where A.candidate="+idCandidate+" and  A.result=:results and A.answerDate is not NULL", Long.class);
		query.setParameter("results", false);

		Long nbr=query.getSingleResult();
		System.out.println(nbr);
		return nbr;
	}

	@Override
	public Long nbrALlCandidate() {
		TypedQuery<Long> query = em.createQuery("select count(*) from Candidate", Long.class);
		Long nbr=query.getSingleResult();
		System.out.println(nbr);
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

		return nbrAppThismonthLong;
	}


	@Override
	public Long NbrApplicationWaitingReplybyCandidate(int idCandidate) {
		TypedQuery<Long> query =em.createQuery
				("select count(A) from Application A where A.candidate="+idCandidate+" and  A.result=:results and A.answerDate is NULL", Long.class);
		query.setParameter("results", false);

		Long nbr=query.getSingleResult();
		System.out.println(nbr);
		return nbr;
	}

	@Override
	public Long nbrJobOfferSkills(String A) {
		Date date = new Date();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		TypedQuery<Long> query3 =em.createQuery("SELECT count(*) FROM JobOffer J Join J.Skills JS where JS.Type=:skill and Month(J.depositDate)=Month(:date)",Long.class);	
		query3.setParameter("skill", A);
		query3.setParameter("date", formatter.format(date));
		Long nbrAppThismonthLong=query3.getSingleResult();

		return nbrAppThismonthLong;
	}

	@Override
	public Long nbrCandidateSkills(String skill) {

		TypedQuery<Long> query3 =em.createQuery("SELECT count(*) FROM Candidate C Join C.Skills CS where CS.Type=:skill ",Long.class);	
		query3.setParameter("skill", skill);
		//query3.setParameter("date", formatter.format(date));
		Long nbrAppThismonthLong=query3.getSingleResult();
		System.out.println(nbrAppThismonthLong);
		return nbrAppThismonthLong;

	}

	

	}
