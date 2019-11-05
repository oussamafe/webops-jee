package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
import com.fasterxml.jackson.annotation.JsonManagedReference;

=======
import com.fasterxml.jackson.annotation.JsonTypeName;
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

=======
import javax.persistence.FetchType; 
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

@Entity
@DiscriminatorValue(value="Candidate")

public class Candidate extends User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String StudyLevel;
	private String ProfilIntro ;
	private String Activities ;
	private int PhoneNumber ;
	private String Certifications ;
	private String Experiences;
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
=======
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	
	@OneToMany(mappedBy="Candidate",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	//@JsonIgnoreProperties("Courses")
	private Set<Course> Courses;
	
	
=======
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	
	private String SubCand;//my subscriptions
	
	private String SubCompany;
	
	private String SubbedCand;//my subscribers
	
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	private String Friends;
	
	private String Friendsrequests;
	
	@ManyToMany(cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	private Set<Skill> Skills;
	

	@OneToMany(mappedBy="Candidate",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	//@JsonIgnoreProperties("Courses")
	private Set<Course> Courses;
	
	
=======
	
	@OneToMany (mappedBy = "Friend", cascade=CascadeType.ALL)
	private Set<Friend> Friends;

	@ManyToMany(cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	private Set<Skill> Skills;
	
=======

	@ManyToOne
	private Post post;
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

	@OneToMany(mappedBy="Candidate",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	//@JsonIgnoreProperties("Courses")
	private Set<Course> Courses;


>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	@OneToMany(mappedBy="Candidate", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<ProfessionalExperience> ProfessionalExperiences;
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	
	@OneToMany (mappedBy = "candidate",cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<Application> job_candidate;
	
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Events> events;
=======

>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	
	//---------------------  add by oussema mahjoub ---------------------//
	@OneToOne(mappedBy = "candidate")
	private AvailabilityCandidate avalibilityCandidate;
	
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	@JsonManagedReference(value="candidatInterview-movement")
	@OneToMany(mappedBy = "candidatInterview", cascade = { CascadeType.ALL } , fetch = FetchType.EAGER)
	private Set<Interview> interviews;

	@OneToOne(mappedBy = "candidatTest")	
	private OnlineTest onlineTest;
	
	//-------------- add by iheb ------------------//
	
	//@JsonManagedReference(value="candidate-post")
	@OneToMany(mappedBy="candidate", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Post> lstPub;
	
	@OneToMany(mappedBy="candidate", cascade=CascadeType.ALL, fetch = FetchType.EAGER)	
	private Set<Comment> lstCom;
	
	public Set<Post> getLstPub() {
		return lstPub;
	}

	public void setLstPub(Set<Post> lstPub) {
		this.lstPub = lstPub;
	}

	public Set<Comment> getLstCom() {
		return lstCom;
	}

	public void setLstCom(Set<Comment> lstCom) {
		this.lstCom = lstCom;
	}
=======
	@OneToMany(mappedBy = "candidatInterview", cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
=======
=======

	@OneToMany (mappedBy = "candidate",cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<Application> job_candidate;



	//---------------------  add by oussema mahjoub ---------------------//
	@OneToOne(mappedBy = "candidate")
	private AvailabilityCandidate avalibilityCandidate;

	@OneToMany(mappedBy = "candidatInterview", cascade = { CascadeType.ALL })
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	private List<Interview> interviews = new ArrayList<>();

	@OneToOne(mappedBy = "candidatTest")	
	private OnlineTest onlineTest;		
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

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

	//-------------------------------------------------------------------//
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	
	
	public String getSubCand() {
		return SubCand;
	}

<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	public String getFriends() {
		return Friends;
	}

	public void setFriends(String friends) {
		Friends = friends;
	}

	public String getFriendsrequests() {
		return Friendsrequests;
	}

	public void setFriendsrequests(String friendsrequests) {
		Friendsrequests = friendsrequests;
	}

=======
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	public String getSubbedCand() {
		return SubbedCand;
	}

	public void setSubbedCand(String subbedCand) {
		SubbedCand = subbedCand;
	}

	public void setSubCand(String subCand) {
		SubCand = subCand;
	}
	
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
=======
	public String getSubCompany() {
		return SubCompany;
	}

	public void setSubCompany(String subCompany) {
		SubCompany = subCompany;
	}
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
=======
=======


>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	public Set<Application> getJob_candidate() {
		return job_candidate;
	}

	public void setJob_candidate(Set<Application> job_candidate) {
		this.job_candidate = job_candidate;
	}
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	
	
=======


>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
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

	
	public Set<Skill> getSkills() {
		return Skills;
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
	}

	public void setSkills(Set<Skill> skills) {
		Skills = skills;
	}

=======
	}

	public void setSkills(Set<Skill> skills) {
		Skills = skills;
	}

>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	public static long getSerialversionuid() {
		return serialVersionUID;
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

<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
=======
<<<<<<< webops-ejb/src/main/java/entities/Candidate.java
>>>>>>> webops-ejb/src/main/java/entities/Candidate.java
	
	
	
=======

>>>>>>> webops-ejb/src/main/java/entities/Candidate.java

}
