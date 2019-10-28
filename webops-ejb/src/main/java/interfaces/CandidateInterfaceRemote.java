package interfaces;

import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;


@Remote
public interface CandidateInterfaceRemote {

	public int addCandidate(Candidate C);
	public void UpdateCandidate(Candidate C	);
	public int UpdateEmailCandidate(int idC,String Email);
	public void RemoveCandidate(int idC);
	public Candidate displayCandidateDetails(int idC)	;
	public void affectCourseCandidate(int CourseId, int CandidateId);
	Set<Course> getAllCourseBycandidate(int CandidateId);
	public void affectProExpCandidate(int ProfExpId, int CandidateId);
	Set<ProfessionalExperience> getAllExpProdBycandidate(int CandidateId);
	public void ToSubScribetoCandidate(int idCandidate,int idSubCan);
	public void ToSubScribetoCompany(int idCandidate,int idSubComp);
	List<String > getAllMyCandidateSubs(int idCandidate);
	List<String > getAllMyCompanySub(int idCandidate);
	List<String> gelAllMysubscribers(int idCandidate);
	public void sendFriendRequest(int idCandidate1,int idCandidate2);
	public void TreatFriendRequest(int idCandidate1,int idCandidate2,int state);
	public void RemoveFriend(int idCandidate,int idFriend);
}
