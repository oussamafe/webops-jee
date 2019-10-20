package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



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
	
	
	@OneToMany(mappedBy="Candidate", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<ProfessionalExperience> ProfessionalExperiences;
	
	@OneToMany (mappedBy = "candidate")
	private List<Application> job_candidate;
	
	public List<Application> getJob_candidate() {
		return job_candidate;
	}

	public void setJob_candidate(List<Application> job_candidate) {
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
