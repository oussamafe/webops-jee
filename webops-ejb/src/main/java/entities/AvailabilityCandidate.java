package entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;



@Entity
public class AvailabilityCandidate extends Availability  {

	
private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Candidate candidate;
	
	public AvailabilityCandidate() {
		super();
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
