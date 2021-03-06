package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class InterviewType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private int hours_number;
	@Enumerated(EnumType.STRING)
	private Role roleOfEmploye ;
	@JsonManagedReference(value="interviewType-movement")
	@OneToMany(mappedBy = "interviewType", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Interview> interviews = new HashSet<Interview>();

	public String getType() {
		return type;
	}

	public Role getRoleOfEmploye() {
		return roleOfEmploye;
	}

	public void setRoleOfEmploye(Role roleOfEmploye) {
		this.roleOfEmploye = roleOfEmploye;
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

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public int getId() {
		return id;
	}

	public InterviewType() {
		super();
	}
}
