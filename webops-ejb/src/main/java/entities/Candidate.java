package entities;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "Candidate")
public class Candidate extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String StudyLevel;
	private String ProfilIntro;
	private String Activities;
	private String skills;
	private int PhoneNumber;
	private String Certifications;
	private String Experiences;

	@OneToMany(mappedBy = "Candidate", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Course> Courses;

	@ManyToMany(mappedBy = "savedOffersCandidate", cascade = CascadeType.ALL)
	private Set<JobOffer> savedOffers;

	@ManyToOne
	private Post post;

	@ManyToMany(mappedBy = "followers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Company> company;

	@OneToMany(mappedBy = "Candidate", fetch = FetchType.EAGER)
	private Set<ProfessionalExperience> ProfessionalExperiences;

	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
	private Set<Application> job_candidate;

	// --------------------- add by oussema mahjoub ---------------------//
	@OneToOne(mappedBy = "candidate")
	private AvailabilityCandidate avalibilityCandidate;

	@OneToMany(mappedBy = "candidatInterview", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Interview> interviews;

	@OneToOne(mappedBy = "candidatTest")
	private OnlineTest onlineTest;

	public AvailabilityCandidate getAvalibilityCandidate() {
		return avalibilityCandidate;
	}

	public void setAvalibilityCandidate(AvailabilityCandidate avalibilityCandidate) {
		this.avalibilityCandidate = avalibilityCandidate;
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

	// -------------------------------------------------------------------//

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

	public Set<Company> getCompany() {
		return company;
	}

	public void setCompany(Set<Company> company) {
		this.company = company;
	}

	public Set<JobOffer> getSavedOffers() {
		return savedOffers;
	}

	public void setSavedOffers(Set<JobOffer> savedOffers) {
		this.savedOffers = savedOffers;
	}

}
