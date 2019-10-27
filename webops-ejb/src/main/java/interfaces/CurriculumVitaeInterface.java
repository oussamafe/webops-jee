package interfaces;

import javax.ejb.Remote;

import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;

@Remote
public interface CurriculumVitaeInterface {
	public int addCourse(Course C);
	public int addExpPro(ProfessionalExperience ProExp);
	public void RemoveCourse(int CourseId,int idCandidate);
	public void RemoveExpPro(int ProExpId,int idCandidate) ;
	public void updatecourse(Course C);
	public void updateExpPro(ProfessionalExperience P);

	
}
