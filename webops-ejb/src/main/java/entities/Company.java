package entities;


<<<<<<< webops-ejb/src/main/java/entities/Company.java
import java.io.Serializable;
<<<<<<< webops-ejb/src/main/java/entities/Company.java
=======
=======
import java.io.Serializable; 
>>>>>>> webops-ejb/src/main/java/entities/Company.java
>>>>>>> webops-ejb/src/main/java/entities/Company.java
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String field;
	private int nbEmployees;
<<<<<<< webops-ejb/src/main/java/entities/Company.java
=======
<<<<<<< webops-ejb/src/main/java/entities/Company.java
>>>>>>> webops-ejb/src/main/java/entities/Company.java
	private String image;
	@Column(unique = true)
	private String email;

	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"password","active","confirmationToken","company","job_candidate","avalibilityCandidate","interviews","onlineTest","profilIntro","activities","phoneNumber","studyLevel","experiences","courses","certifications","professionalExperiences","skills","email"})
	private Set<Candidate> followers ;	
	//@JsonManagedReference
<<<<<<< webops-ejb/src/main/java/entities/Company.java
=======
=======
	
	@Column(columnDefinition = "integer default 5")
	private int nbroffres=5;
	
	public int getNbroffres() {
		return nbroffres;
	}
	public void setNbroffres(int nbroffres) {
		this.nbroffres = nbroffres;
	}
	

	@JsonManagedReference
>>>>>>> webops-ejb/src/main/java/entities/Company.java
>>>>>>> webops-ejb/src/main/java/entities/Company.java
	@OneToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL},mappedBy="company")
	@JsonIgnoreProperties({"id","password","confirmationToken","availabilityEmploye","company","interviews"})
	private Set<Employe> employes;
	
	@OneToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL},mappedBy="company_offers")
	private Set<JobOffer> comapnyJobs;
	
<<<<<<< webops-ejb/src/main/java/entities/Company.java
	@OneToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL},mappedBy="company")
	private Set<Events> events;
	
	
=======
>>>>>>> webops-ejb/src/main/java/entities/Company.java
	public Set<JobOffer> getComapnyJobs() {
		return comapnyJobs;
	}

	public void setComapnyJobs(Set<JobOffer> comapnyJobs) {
		this.comapnyJobs = comapnyJobs;
	}

	public Company(String name, String field, int nbEmployees) {
		super();
		this.name = name;
		this.field = field;
		this.nbEmployees = nbEmployees;
	}

	public Company() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getNbEmployees() {
		return nbEmployees;
	}

	public void setNbEmployees(int nbEmployees) {
		this.nbEmployees = nbEmployees;
	}

	public Set<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Candidate> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Candidate> followers) {
		this.followers = followers;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

<<<<<<< webops-ejb/src/main/java/entities/Company.java
	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

=======
>>>>>>> webops-ejb/src/main/java/entities/Company.java
	


}


	
	
	