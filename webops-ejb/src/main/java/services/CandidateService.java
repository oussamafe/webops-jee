package services;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import entities.Candidate;
import entities.Course;

import entities.ProfessionalExperience;
import interfaces.CandidateInterfaceRemote;
@Stateless
@LocalBean
public class CandidateService implements CandidateInterfaceRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	
	@Override
	public int addCandidate(Candidate C) {
		
		if(ValidateMail(C.getEmail())==0) {
		em.persist(C);
		String pass = BCrypt.hashpw(C.getPassword(), BCrypt.gensalt());
		C.setPassword(pass);
		return C.getId();
		}
		return 0 ;
	}

	
	 public Long ValidateMail(String Email) {
		 TypedQuery<Long> query = em.createQuery("select count(*) from Candidate e where e.email=:Cemail", Long.class);
			query.setParameter("Cemail",Email);
			Long nbr=query.getSingleResult();
			return nbr;
			
	 }
   // à verifier avec email unique
	@Override
	public int UpdateEmailCandidate(int idC,String Email) {
		
	
		if(ValidateMail(Email)==0) {
			Query query2 = em.createQuery("update Candidate C set C.email=:Email where C.id=:Candidateid");
			query2.setParameter("Email", Email);
			query2.setParameter("Candidateid", idC);
			int modified = query2.executeUpdate();
			return modified;

		} return 0;
		
	}


	@Override
	public void UpdateCandidate(Candidate C	) {
		Candidate candidate = em.find(Candidate.class, C.getId()); 
		candidate.setFirst_Name(C.getFirst_Name()); 
		candidate.setLast_Name(C.getLast_Name());
		candidate.setExperiences(C.getExperiences());
		candidate.setStudyLevel(C.getStudyLevel());
		candidate.setSkills(C.getSkills());
		candidate.setProfilIntro(C.getProfilIntro());
		candidate.setPhoneNumber(C.getPhoneNumber());
		candidate.setCertifications(C.getCertifications());
		
	}
	//methode facultative
	@Override
	public void RemoveCandidate(int idC) {
		em.remove(em.find(Candidate.class, idC));


	}

	@Override
	public Candidate displayCandidateDetails(int idC) {
		Candidate candidate = em.find(Candidate.class, idC);
		if(candidate != null)
			return candidate;
		return null;
	}

	@Override
	public void affectCourseCandidate(int CourseId, int CandidateId) {
		Candidate CandidateManagedEntity = em.find(Candidate.class, CandidateId);
		Course CourseManagedEntity = em.find(Course.class, CourseId);
		CourseManagedEntity.setCandidate(CandidateManagedEntity);



	}

	

	@Override
	public Set<Course> getAllCourseBycandidate(int CandidateId) {
		Candidate candidateManagedEntity = em.find(Candidate.class, CandidateId);
		Set<Course> Courses = new HashSet<Course>();
		for(Course C : candidateManagedEntity.getCourses()){
			Courses.add(C);
			
		}
		
		return Courses;
	}

	@Override
	public void affectProExpCandidate(int ProfExpId, int CandidateId) {
		Candidate CandidateManagedEntity = em.find(Candidate.class, CandidateId);
		ProfessionalExperience ProfessionalExperienceManagedEntity = em.find(ProfessionalExperience.class, ProfExpId);
		ProfessionalExperienceManagedEntity.setCandidate(CandidateManagedEntity);

	}


	@Override
	public Set<ProfessionalExperience> getAllExpProdBycandidate(int CandidateId) {
		Candidate candidateManagedEntity = em.find(Candidate.class, CandidateId);
		Set<ProfessionalExperience> ExpProf = new HashSet<ProfessionalExperience>();
		for(ProfessionalExperience e : candidateManagedEntity.getProfessionalExperiences()){
			ExpProf.add(e);
		}
		
		return ExpProf;

	}


	@Override
	public void ToSubScribetoCandidate(int idCandidate, int idSub) {
		Query query2 = em.createQuery("update Candidate C set C.SubCand=:id where C.id=:Candidateid");
		query2.setParameter("id", idSub+"|");
		query2.setParameter("Candidateid", idCandidate);
		query2.executeUpdate();
		
	}


	@Override
	public void ToSubScribetoCompany(int idCandidate, int idSubComp) {
		Query query2 = em.createQuery("update Candidate C set C.SubCompany=:id where C.id=:Candidateid");
		query2.setParameter("id", idSubComp+"|");
		query2.setParameter("Candidateid", idCandidate);
		query2.executeUpdate();
		
	}

}
