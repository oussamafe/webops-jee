package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class OnlineTest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal (TemporalType.DATE)
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private StateTestOnline state;
	
	@JsonBackReference(value="candidatTest-movement")
	@OneToOne
	private Candidate candidatTest;	
	
	private int note;
	
	

	@JsonBackReference(value="questions-movement")
	@ManyToMany
	private Set<Question> questions ;
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	
	
	public OnlineTest() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Candidate getCandidatTest() {
		return candidatTest;
	}

	public void setCandidatTest(Candidate candidatTest) {
		this.candidatTest = candidatTest;
	}

	public StateTestOnline getState() {
		return state;
	}

	public void setState(StateTestOnline state) {
		this.state = state;
	}

	

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public int getId() {
		return id;
	}
	
}
