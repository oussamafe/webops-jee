package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	//@JsonBackReference(value="candidate-post")
	@ManyToOne
	@JoinColumn(name = "candidate")
	@JsonIgnoreProperties({"Courses","ProfessionalExperiences","job_candidate","avalibilityCandidate","interviews","onlineTest","lstPub","lstCom"})
	private Candidate candidate;

	
	@OneToMany(mappedBy = "pub", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<Comment> lstComm ;

	public Post() {
		super();
	}

	public Set<Comment> getLstComm() {
		return lstComm;
	}

	public void setLstComm(Set<Comment> lstComm) {
		this.lstComm = lstComm;
	}

	public Post(Long id, String description, Date dateCreation) {
		super();
		this.id = id;
		this.description = description;
		this.dateCreation = dateCreation;
	}

	public Post(String description, Date dateCreation) {
		super();
		this.description = description;
		this.dateCreation = dateCreation;
	}
	public Post(String description) {
		super();
		this.description = description;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", dateCreation=" + dateCreation + ", candidate="
				+ candidate + ", lstComm=" + lstComm + "]";
	}


}
