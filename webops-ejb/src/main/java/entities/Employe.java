package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@DiscriminatorValue(value="Employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Employe extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Role role ;
	
	
	//------------------------add by oussema mahjoub--------------//
	@JsonManagedReference(value="availabilityEmploye-movement")
	@OneToMany(mappedBy = "employe", cascade = { CascadeType.ALL })
	private Set<AvailabilityEmploye> availabilityEmploye;
		
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy="employeInterview", cascade = {CascadeType.ALL})
	private Set<Interview> interviews;
	
	public Set<AvailabilityEmploye> getAvailabilityEmploye() {
		return availabilityEmploye;
	}

	public void setAvailabilityEmploye(Set<AvailabilityEmploye> availabilityEmploye) {
		this.availabilityEmploye = availabilityEmploye;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}	
	//-----------------------------------------------------------//
	
	
	
	

	//@JsonBackReference
	@ManyToOne
	@JsonIgnoreProperties("employes")
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

	@JsonGetter
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
		
	
}
