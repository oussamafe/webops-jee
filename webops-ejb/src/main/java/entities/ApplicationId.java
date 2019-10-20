package entities;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ApplicationId implements Serializable{

	@ManyToOne
	@JoinColumn(name = "idJobOffer", referencedColumnName = "id", insertable = false, updatable = false)
	private JobOffer offer;

	@ManyToOne
	@JoinColumn(name = "idCandidate", referencedColumnName = "id", insertable = false, updatable = false)
	private Candidate candidate;
	
	public JobOffer getOffer() {
		return offer;
	}

	public void setOffer(JobOffer offer) {
		this.offer = offer;
	}

	
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
}
