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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Application implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	ApplicationId id;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name="idJobOffer" )
	JobOffer jobOffer;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name="idCandidate")
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
	@JsonIgnore
	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}
	@JsonIgnore
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerDate == null) ? 0 : answerDate.hashCode());
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
		result = prime * result + ((depositDate == null) ? 0 : depositDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jobOffer == null) ? 0 : jobOffer.hashCode());
		result = prime * result + (this.result ? 1231 : 1237);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (answerDate == null) {
			if (other.answerDate != null)
				return false;
		} else if (!answerDate.equals(other.answerDate))
			return false;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
			return false;
		if (depositDate == null) {
			if (other.depositDate != null)
				return false;
		} else if (!depositDate.equals(other.depositDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobOffer == null) {
			if (other.jobOffer != null)
				return false;
		} else if (!jobOffer.equals(other.jobOffer))
			return false;
		if (result != other.result)
			return false;
		return true;
	}



	public Application(ApplicationId id, JobOffer jobOffer, Candidate candidate, Date depositDate, Date answerDate,
			boolean result) {
		super();
		this.id = id;
		this.jobOffer = jobOffer;
		this.candidate = candidate;
		this.depositDate = depositDate;
		this.answerDate = answerDate;
		this.result = result;
	}

}
