package entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AvailabilityCandidate extends Availability {

	private static final long serialVersionUID = 1L;

	@JsonBackReference(value="avalibilityCandidate-movement")
	@ManyToOne
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
