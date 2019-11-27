package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** estimated time for responce to this question */
	private int estimated_Time;
	private String question;
	/**
	 * module of question exemple : javaee , python,c++... . it help on research
	 */
	private String Module;
	
	
	
	
	
	
	
	//@JsonManagedReference(value="questions-movement")
	@ManyToMany(mappedBy = "questions", cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	private Set<OnlineTest> onlineTests;
	
	
	
	
	
	
	
	
	@JsonManagedReference(value="questionReponce-movement")
	@OneToMany(mappedBy = "questionReponce", cascade = { CascadeType.ALL },fetch = FetchType.EAGER)	
	private Set<Responce> reponces= new HashSet<Responce>();

	public Question() {
		super();
	}

	public int getEstimated_Time() {
		return estimated_Time;
	}

	public void setEstimated_Time(int estimated_Time) {
		this.estimated_Time = estimated_Time;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getModule() {
		return Module;
	}

	public void setModule(String module) {
		Module = module;
	}

	public Set<OnlineTest> getOnlineTests() {
		return onlineTests;
	}

	public void setOnlineTests(Set<OnlineTest> onlineTests) {
		this.onlineTests = onlineTests;
	}

	public Set<Responce> getReponces() {
		return reponces;
	}

	public void setReponces(Set<Responce> reponces) {
		this.reponces = reponces;
	}

	public int getId() {
		return id;
	}
	
}
