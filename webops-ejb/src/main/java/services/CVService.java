package services;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;
import entities.Skill;
import entities.User;
import interfaces.CurriculumVitaeInterface;
@Stateless
@LocalBean
public class CVService implements CurriculumVitaeInterface {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	@Override
	public int addCourse(Course C) {
		em.persist(C);
		
		return C.getId();

	}

	@Override
	public int addExpPro(ProfessionalExperience ProExp) {
		em.persist(ProExp);
		return ProExp.getId();
	}

	@Override
	public  void RemoveCourse(int CourseId,int idCandidate) {
		int query=
				em.createQuery("Delete from Course C where C.Candidate="+idCandidate+" and C.id="+CourseId )
				  .executeUpdate();

	}

	@Override
	public void RemoveExpPro(int ProExpId,int idCandidate) {
		int query=
				em.createQuery("Delete from  ProfessionalExperience P where P.Candidate="+idCandidate+" and P.id="+ProExpId )
				  .executeUpdate();
	}

	@Override
	public void updatecourse(Course C) {
		Course course=em.find(Course.class,C.getId());
		course.setDiploma(C.getDiploma());
		course.setInstitution(C.getInstitution());
		course.setStartDate(C.getStartDate());
		course.setEndDate(C.getEndDate());


	}

	@Override
	public void updateExpPro(ProfessionalExperience P) {
		ProfessionalExperience Prof = em.find(ProfessionalExperience.class, P.getId());
		Prof.setDescription(P.getDescription());
		Prof.setStartDate(P.getStartDate());
		Prof.setEndDate(P.getEndDate());
		Prof.setPlace(P.getPlace());

	}

	@Override
	public int addSkills(Skill S) {
		em.persist(S);
		
		return S.getId();
		
	}

	@Override
	public void affectCandidateSkill(int idCandidate, int idskill) {
		Query query= em.createNativeQuery
				("INSERT INTO user_skill (Skills_id, Candidates_id) VALUES("+idskill+"," +idCandidate+")");
		query.executeUpdate();

		
	}
	

	@Override
	public void desaffecterCandidateSkill(int idCandidate, int idskill) {
	/*	Skill skill = em.find(Skill.class, idskill);
		
		int candidatesnb = skill.getCandidates().size();
		for(int index = 0; index < candidatesnb; index++){
			if(skill.getCandidates().get(index).getId() == idCandidate){
				skill.getCandidates().remove(index);
				break;//a revoir
			}
		}*/
		
		//em.merge(dep);
	}

	@Override
	public List<Skill> getAllSkills() {
		TypedQuery<Skill> query = em.createQuery("select S from Skill S", Skill.class);
		List<Skill> S=query.getResultList()	;
		return S;
	}


	

	
}
