package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Interview implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal (TemporalType.DATE)
	private Date date;
	private int startHour;
	
	@Enumerated(EnumType.STRING)
	private StateTestOnline state;
	
	
	public StateTestOnline getState() {
		return state;
	}


	public void setState(StateTestOnline state) {
		this.state = state;
	}


	public int getStartHour() {
		return startHour;
	}


	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}


	@JsonBackReference(value="employeInterview-movement")
	@ManyToOne
	private Employe employeInterview;
	
	@JsonBackReference(value="candidatInterview-movement")
	@ManyToOne
	private Candidate candidatInterview;
	
	@JsonBackReference(value="interviewType-movement")
	@ManyToOne
	private InterviewType interviewType;
	
	
	public Interview() {
		super();
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Employe getEmployeInterview() {
		return employeInterview;
	}


	public void setEmployeInterview(Employe employeInterview) {
		this.employeInterview = employeInterview;
	}


	public Candidate getCandidatInterview() {
		return candidatInterview;
	}


	public void setCandidatInterview(Candidate candidatInterview) {
		this.candidatInterview = candidatInterview;
	}


	public InterviewType getInterviewType() {
		return interviewType;
	}


	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}


	public int getId() {
		return id;
	}
	
}
