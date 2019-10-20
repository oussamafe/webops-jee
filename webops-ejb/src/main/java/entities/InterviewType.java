package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class InterviewType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private int hours_number;

	@OneToMany(mappedBy = "interviewType", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Interview> interviews = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHours_number() {
		return hours_number;
	}

	public void setHours_number(int hours_number) {
		this.hours_number = hours_number;
	}

	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public int getId() {
		return id;
	}

	public InterviewType() {
		super();
	}
}