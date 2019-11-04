package entities;

import java.io.Serializable;
import java.util.List; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class JobOffer implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;

	private String description;
	
	private String job_title;
	
	private String skills;
	
	private String type;
	
	private String level;
	
	private String location;
	
	@ManyToOne
	private Company company_offers;
	
	@OneToMany (mappedBy = "jobOffer")
	private List<Application> job_offer;
	
	
	public List<Application> getJob_offer() {
		return job_offer;
	}

	public void setJob_offer(List<Application> job_offer) {
		this.job_offer = job_offer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Company getCompany_offers() {
		return company_offers;
	}

	public void setCompany_offers(Company company_offers) {
		this.company_offers = company_offers;
	}

	

	public JobOffer() {
		super();
	}
	
	
	
	
}
