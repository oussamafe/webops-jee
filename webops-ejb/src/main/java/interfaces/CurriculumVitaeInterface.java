package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Course;
import entities.ProfessionalExperience;
import entities.Skill;

@Remote
public interface CurriculumVitaeInterface {
	public int addCourse(Course C);
	public int addExpPro(ProfessionalExperience ProExp);
	public void RemoveCourse(int CourseId,int idCandidate);
	public void RemoveExpPro(int ProExpId,int idCandidate) ;
	public void updatecourse(Course C);
	public void updateExpPro(ProfessionalExperience P);
	public int addSkills(Skill S);
	public void affectCandidateSkill(int idCandidate, int skill);
	public void desaffecterCandidateSkill(int idCandidate, int skill);
	public List<Skill>getAllSkills();
}
