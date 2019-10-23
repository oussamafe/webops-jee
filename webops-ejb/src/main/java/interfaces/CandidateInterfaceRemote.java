package interfaces;

import java.util.Set;
import javax.ejb.Remote;
import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;


@Remote
public interface CandidateInterfaceRemote {

	public int addCandidate(Candidate C);
	public void UpdateCandidate(Candidate C);
	public int UpdateEmailCandidate(int idC,String Email);
	public void RemoveCandidate(int idC);
	public Candidate displayCandidateDetails(int idC)	;
	public void affectCourseCandidate(int CourseId, int CandidateId);
	Set<Course> getAllCourseBycandidate(int CandidateId);
	public void affectProExpCandidate(int ProfExpId, int CandidateId);
	Set<ProfessionalExperience> getAllExpProdBycandidate(int CandidateId);
	
	
}
