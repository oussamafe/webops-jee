package entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Application implements Serializable {
	
	@EmbeddedId
	ApplicationId id;
	
	@ManyToOne
	@MapsId("idJobOffer")
	@JoinColumn(name="idJobOffer")
	JobOffer jobOffer;
	
	@ManyToOne
	@MapsId("idCandiate")
	@JoinColumn(name="idCandiate")
	Candidate candidate;
	
	@Temporal(TemporalType.DATE)
	private Date depositDate;

	@Temporal(TemporalType.DATE)
	private Date answerDate;

	private boolean result;
	

	public Application() {
		super();
	}
	
	

	public ApplicationId getId() {
		return id;
	}

	public void setId(ApplicationId id) {
		this.id = id;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

}
