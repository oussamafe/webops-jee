package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.*;

@Singleton
public class WorkMail {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	 
	public List<JobOffer> GetAllJobOffersByWeek( ) throws MessagingException {
		Date date = new Date();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Skill> SkillsJob=new ArrayList<Skill>();
		List<String> S=new ArrayList<String>()	;
		List<Skill> SkillsCandidate= new ArrayList<Skill>();
		TypedQuery<Candidate> query3 =em.createQuery("SELECT C FROM Candidate C ",Candidate.class);	
		List<Candidate> CandidateList=query3.getResultList()	;
		TypedQuery<JobOffer> query4 =em.createQuery("SELECT J FROM JobOffer J  where Month(J.depositDate)=Month(:date)",JobOffer.class);	
		query4.setParameter("date", formatter.format(date));
		List<JobOffer>JObOfferList=query4.getResultList()	;
		
		for (int m = 0; m < CandidateList.size(); m++) 
		{	
		S.clear();
		SkillsCandidate.clear();
		SkillsCandidate.addAll(CandidateList.get(m).getSkills());
		 
		
		
		for (int i = 0; i < JObOfferList.size(); i++) 
			{
			SkillsJob.clear();	
			SkillsJob.addAll(JObOfferList.get(i).getSkills());	
			for(int j=0 ; j < SkillsCandidate.size() ; j++){
			for(int y=0 ; y < SkillsJob.size() ; y++)
			{
			if(SkillsJob.get(y).getId()==SkillsCandidate.get(j).getId())
			{	
				S.add(JObOfferList.get(i).getJob_title());
				break;		
			}
			
			}
			
			}
			}
			JavaMail.sendConfirmationAcount(CandidateList.get(m).getEmail(),S.toString());
			
			}

		
		
		
	
		return null;
		
	

	}

	}


