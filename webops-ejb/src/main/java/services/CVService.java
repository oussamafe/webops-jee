package services;

import java.text.SimpleDateFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;
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
	public  void RemoveCourse(int CourseId) {
		em.remove(em.find(Course.class, CourseId));

	}

	@Override
	public void RemoveExpPro(int ProExpId) {
		em.remove(em.find(ProfessionalExperience.class, ProExpId));
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
	public int addCurriculumVitae(Candidate Candidate, int idC, Course C, ProfessionalExperience PE) {
			Candidate candidate=em.find(Candidate.class, Candidate.getId());
			
		
		
		return 0;
	}
}
