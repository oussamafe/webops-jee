package entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class AvailabilityEmploye extends Availability {

	private static final long serialVersionUID =1L;
	
	@JsonBackReference(value="availabilityEmploye-movement")
	@ManyToOne
	private Employe employe;
	
	public AvailabilityEmploye() {
		super();
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	
}
