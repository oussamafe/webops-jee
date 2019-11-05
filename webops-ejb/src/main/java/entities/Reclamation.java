package entities;
 
import java.util.Date; 
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;  

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Gazzah
 *
 */
@DiscriminatorValue(value="Reclamation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Reclamation  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String sujet;
	private String libelle;
	private String message;
	private String reponse; 
	private Date dateDeTraitement; 
	
	 
	@JsonBackReference(value="user-id")
	@ManyToOne
	private User user; 
	
	@JsonBackReference(value="employe-id")
	@ManyToOne
	private Employe WhoDoneIt; 
	
	@JsonBackReference(value="company-id")
	@ManyToOne
	private Company company;

	 
	public Employe getWhoDoneIt() {
		return WhoDoneIt;
	}
	public void setWhoDoneIt(Employe whoDoneIt) {
		WhoDoneIt = whoDoneIt;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	@Column(columnDefinition="varchar(250) DEFAULT 'En_Attent'")
	@Enumerated(EnumType.STRING)
	private Etat etat;
	
	@Enumerated(EnumType.STRING)
	private ClaimType claimtype;
	
	@Column(name="date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//	@Temporal (TemporalType.DATE)
	private Date date;
	

	
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Reclamation [id=" + id + ", sujet=" + sujet + ", libelle=" + libelle + ", message=" + message
				+ ", reponse=" + reponse + ", dateDeTraitement=" + dateDeTraitement + ", user=" + user + ", WhoDoneIt="
				+ WhoDoneIt + ", company=" + company + ", etat=" + etat + ", claimtype=" + claimtype + ", date=" + date
				+ "]";
	}
	 
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public ClaimType getClaimtype() {
		return claimtype;
	}
	public void setClaimtype(ClaimType claimtype) {
		this.claimtype = claimtype;
	}
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
 
	public Date getDate() {
		return date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	public Date getDateDeTraitement() {
		return dateDeTraitement;
	}
	public void setDateDeTraitement(Date dateDeTraitement) {
		this.dateDeTraitement = dateDeTraitement;
	}
 
	
 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
 

 
 
	
}
