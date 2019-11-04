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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "company_offers", "job_offer" })
public class JobOffer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	private String job_title;


	private String type;

	private String level;

	private String location;

	private boolean approved;

	private String approvalDetails;

	private Date approvalDate;

	private Date depositDate;

	private boolean available;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Candidate> savedOffersCandidate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Skill> Skills;
	
	public Set<Skill> getSkills() {
		return Skills;
	}

	public void setSkills(Set<Skill> skills) {
		Skills = skills;
	}

	@ManyToOne
	@JsonIgnoreProperties({ "company" })
	Employe submittedBy;

	@ManyToOne
	private Company company_offers;

	@OneToMany(mappedBy = "jobOffer", fetch = FetchType.EAGER)
	private Set<Application> job_offer;

	public JobOffer() {
		super();
	}

	public Set<Application> getJob_offer() {
		return job_offer;
	}

	public void setJob_offer(Set<Application> job_offer) {
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovalDetails() {
		return approvalDetails;
	}

	public void setApprovalDetails(String approvalDetails) {
		this.approvalDetails = approvalDetails;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Employe getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(Employe submittedBy) {
		this.submittedBy = submittedBy;
	}

	

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

}
