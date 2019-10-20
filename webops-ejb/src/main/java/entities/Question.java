package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



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

	@ManyToMany(mappedBy = "questions")
	private List<OnlineTest> onlineTests;

	@OneToMany(mappedBy = "questionReponce")
	private List<Reponce> reponces;

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

	public List<OnlineTest> getOnlineTests() {
		return onlineTests;
	}

	public void setOnlineTests(List<OnlineTest> onlineTests) {
		this.onlineTests = onlineTests;
	}

	public List<Reponce> getReponces() {
		return reponces;
	}

	public void setReponces(List<Reponce> reponces) {
		this.reponces = reponces;
	}

	public int getId() {
		return id;
	}
	
}
