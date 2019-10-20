package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@DiscriminatorValue(value="Employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Employe extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Role role ;
	
	
	//------------------------add by oussema mahjoub--------------//
	@OneToOne(mappedBy = "employe")
	private AvailabilityEmploye availabilityEmploye;	
	
	@OneToMany(mappedBy="employeInterview", cascade = {CascadeType.ALL})
	private List<Interview> interviews = new ArrayList<>();
	
	public AvailabilityEmploye getAvailabilityEmploye() {
		return availabilityEmploye;
	}

	public void setAvailabilityEmploye(AvailabilityEmploye availabilityEmploye) {
		this.availabilityEmploye = availabilityEmploye;
	}

	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}	
	//-----------------------------------------------------------//
	
	
	
	

	//@JsonBackReference
	@ManyToOne
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
	
	
	
	
	
	
}
