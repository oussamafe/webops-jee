package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String Type;
	@ManyToMany(mappedBy="Skills", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Candidate> Candidates;

	@ManyToMany(mappedBy="Skills",cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	private Set<JobOffer> JobOffers;
	@JsonIgnore
	public Set<Candidate> getCandidates() {
		return Candidates;
	}
	public void setCandidates(Set<Candidate> candidates) {
		Candidates = candidates;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
