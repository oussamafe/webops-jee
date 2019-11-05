package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Events implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@NotBlank
	private String type;
	@NotBlank
	private String title;
	@NotBlank
	private String Description;
	@NotBlank
	private int nbParticipants;
	@NotBlank
	private String location;
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Skill> skills;
	
	@ManyToOne
	private Company company;
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Candidate> participants;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getNbParticipants() {
		return nbParticipants;
	}
	public void setNbParticipants(int nbParticipants) {
		this.nbParticipants = nbParticipants;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Set<Candidate> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Candidate> participants) {
		this.participants = participants;
	}
	public Events() {
		super();
	}
	
	
	
}
