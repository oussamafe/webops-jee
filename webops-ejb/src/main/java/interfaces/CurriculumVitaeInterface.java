package interfaces;

import javax.ejb.Remote;

import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;

@Remote
public interface CurriculumVitaeInterface {
	public int addCourse(Course C);
	public int addExpPro(ProfessionalExperience ProExp);
	public void RemoveCourse(int CourseId);
	public void RemoveExpPro(int ProExpId);
	public void updatecourse(Course C);
	public void updateExpPro(ProfessionalExperience P);
	public int addCurriculumVitae(Candidate Candidate,int idC, Course C, ProfessionalExperience PE);
}
