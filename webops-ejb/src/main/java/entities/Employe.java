package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

<<<<<<< webops-ejb/src/main/java/entities/Employe.java
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
>>>>>>> webops-ejb/src/main/java/entities/Employe.java
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@DiscriminatorValue(value="Employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
//@JsonIgnoreProperties({"password","confirmationToken","availabilityEmploye","interviews","jobsSubmitted"})
public class Employe extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Role role ;
	
<<<<<<< webops-ejb/src/main/java/entities/Employe.java
	@OneToMany(cascade = CascadeType.ALL, mappedBy="submittedBy" , fetch = FetchType.EAGER )
	
	private Set<JobOffer> jobsSubmitted;
	
=======
	/*
>>>>>>> webops-ejb/src/main/java/entities/Employe.java
	//------------------------add by oussema mahjoub--------------//
	@OneToOne(mappedBy = "employe")
	private AvailabilityEmploye availabilityEmploye;	
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy="employeInterview", cascade = {CascadeType.ALL})
	private Set<Interview> interviews;
	
	
	
	public AvailabilityEmploye getAvailabilityEmploye() {
		return availabilityEmploye;
	}

	public void setAvailabilityEmploye(AvailabilityEmploye availabilityEmploye) {
		this.availabilityEmploye = availabilityEmploye;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}	
	//-----------------------------------------------------------//
	
	
	*/
	

	@JsonBackReference
	@ManyToOne
<<<<<<< webops-ejb/src/main/java/entities/Employe.java
	@JsonIgnoreProperties({"employes","nbEmployees","image","followers"})
=======
>>>>>>> webops-ejb/src/main/java/entities/Employe.java
	Company company;
	
	public Employe(Role role) {
		super();
		this.role = role;
	}
	
	public Employe() {
		super();
	}
	

	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}


	public Employe(String first_Name, String last_Name, String email, String password, Role role) {
		super(first_Name, last_Name, email, password);
		this.role = role;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<JobOffer> getJobsSubmitted() {
		return jobsSubmitted;
	}

	public void setJobsSubmitted(Set<JobOffer> jobsSubmitted) {
		this.jobsSubmitted = jobsSubmitted;
	}
		
	
	
	
}
