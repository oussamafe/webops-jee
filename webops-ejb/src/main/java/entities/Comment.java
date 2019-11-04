package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment {
	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", description=" + description + ", dateCreation=" + dateCreation + ", pub="
				+ pub + ", candidate=" + candidate + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@ManyToOne 
	@JoinColumn(name="id_pub")
	private Post pub;
	
	@ManyToOne 
	@JoinColumn(name="id_user")
	private Candidate candidate;

	
	
	
	public Comment() {
		super();
	}
	
	public Comment(Post pub) {
		this.pub = pub;
	}

	public Comment(long id, String description, Post pub, Candidate candidate) {
		super();
		this.id = id;
		this.description = description;
		this.pub = pub;
		this.candidate = candidate;
	}

	public Comment(String description, Post pub, Candidate candidate) {
		super();
		this.description = description;
		this.pub = pub;
		this.candidate = candidate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Post getPub() {
		return pub;
	}

	public void setPub(Post pub) {
		this.pub = pub;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}


}
