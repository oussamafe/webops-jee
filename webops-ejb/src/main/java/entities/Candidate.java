package entities;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;





@Entity
@DiscriminatorValue(value="Candidate")
public class Candidate extends User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String StudyLevel;
	private String ProfilIntro ;
	private String Activities ;
	private String skills;
	private int PhoneNumber ;
	private String Certifications ;
	private String Experiences;

	@OneToMany(mappedBy="Candidate",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	private Set<Course> Courses;
	

	@ManyToOne
	private Post post;

	// add bby oussema mahjoub
	@OneToMany(mappedBy = "Candidate", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	//--
	private Set<ProfessionalExperience> ProfessionalExperiences;
	
	
	// add bby oussema mahjoub
	@JsonManagedReference(value="candidate-movement")
	//--
	@OneToMany (mappedBy = "candidate", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Application> job_candidate;
	
	
	//---------------------  add by oussema mahjoub ---------------------//
	@JsonManagedReference(value="avalibilityCandidate-movement")
	@OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<AvailabilityCandidate> avalibilityCandidate = new HashSet<AvailabilityCandidate>();
	
	@JsonManagedReference(value="candidatInterview-movement")
	@OneToMany(mappedBy = "candidatInterview", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Interview> interviews = new HashSet<Interview>();

	@OneToOne(mappedBy = "candidatTest")	
	private OnlineTest onlineTest;		
	
	

	public void setAvalibilityCandidate(Set<AvailabilityCandidate> avalibilityCandidate) {
		this.avalibilityCandidate = avalibilityCandidate;
	}

	public Set<AvailabilityCandidate> getAvailabilityCandidate() {
		return avalibilityCandidate;
	}
	
	

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public OnlineTest getOnlineTest() {
		return onlineTest;
	}

	public void setOnlineTest(OnlineTest onlineTest) {
		this.onlineTest = onlineTest;
	}

	//-------------------------------------------------------------------//
	
	
	
	public Set<Application> getJob_candidate() {
		return job_candidate;
	}

	public void setJob_candidate(Set<Application> job_candidate) {
		this.job_candidate = job_candidate;
	}
	

	
	public String getStudyLevel() {
		return StudyLevel;
	}

	

	public void setStudyLevel(String studyLevel) {
		StudyLevel = studyLevel;
	}

	public Set<Course> getCourses() {
		return Courses;
	}

	public void setCourses(Set<Course> courses) {
		Courses = courses;
	}
	
	

	public Set<ProfessionalExperience> getProfessionalExperiences() {
		return ProfessionalExperiences;
	}

	public void setProfessionalExperiences(Set<ProfessionalExperience> professionalExperiences) {
		ProfessionalExperiences = professionalExperiences;
	}

	public String getExperiences() {
		return Experiences;
	}

	public void setExperiences(String experiences) {
		Experiences = experiences;
	}

	public String getProfilIntro() {
		return ProfilIntro;
	}

	public void setProfilIntro(String profilIntro) {
		ProfilIntro = profilIntro;
	}

	public String getActivities() {
		return Activities;
	}

	public void setActivities(String activities) {
		Activities = activities;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getCertifications() {
		return Certifications;
	}

	public void setCertifications(String certifications) {
		Certifications = certifications;
	}

	public Candidate() {
		super();
	}

	public Candidate(String first_Name, String last_Name, String email, String password) {
		super(first_Name, last_Name, email, password);
	}
	
	

}
