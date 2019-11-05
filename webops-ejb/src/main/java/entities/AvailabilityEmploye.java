package entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;



@Entity
public class AvailabilityEmploye extends Availability {

	private static final long serialVersionUID =1L;
	
	@OneToOne
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
