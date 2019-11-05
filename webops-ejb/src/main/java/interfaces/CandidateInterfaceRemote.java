package interfaces;

import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;

import javax.ejb.Remote;
import entities.Candidate;
import entities.Company;
import entities.Course;
import entities.JobOffer;
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
	public void ToRemoveCandidateSub(int idCandidate,int idSub);
	public void ToSubScribetoCompany(int idCandidate,int idSubComp);
	public void ToRemoveCompanySub(int idCandidate,int idCompany);
	List<String > getAllMyCandidateSubs(int idCandidate);
	List<String > getAllMyCompanySub(int idCandidate);
	List<String> getAllMysubscribers(int idCandidate);
	List<Candidate> SearchCandidateSkills( String str);
	List<Company> SearchCompanyByNameField(String str);
	List<JobOffer> SearchJobOfferMultipe(String str);
	
}
