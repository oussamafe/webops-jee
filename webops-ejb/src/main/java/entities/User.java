package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

<<<<<<< webops-ejb/src/main/java/entities/User.java
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
=======
import javax.persistence.CascadeType; 
import javax.persistence.DiscriminatorColumn; 
>>>>>>> webops-ejb/src/main/java/entities/User.java
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type") 
public class User implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String first_Name;
	
	private String last_Name;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	private boolean active;

	public List<Reclamation> getReclaime() {
		return reclaime;
	}
	public void setReclaime(List<Reclamation> reclaime) {
		this.reclaime = reclaime;
	}
	public Set<Panier> getPanier() {
		return panier;
	}
	public void setPanier(Set<Panier> panier) {
		this.panier = panier;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@OneToMany (mappedBy = "user", cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	private List<Reclamation> reclaime;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy="userpanier", cascade = {CascadeType.ALL})
	private Set<Panier> panier;
	
	private String confirmationToken;
	
	@Column(columnDefinition="LONGTEXT")
	private String refresh_token;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String first_Name, String last_Name, String email, String password) {
		super();
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.password = password;
		this.active = false;
	}
	
	
	public User() {
		super();
		this.active=false;
	}
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
<<<<<<< webops-ejb/src/main/java/entities/User.java
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", confirmationToken=" + confirmationToken
				+ ", refresh_token=" + refresh_token + "]";
	}
	
	
	
=======
	@Override
	public String toString() {
		return "User [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", reclaime=" + reclaime + "]";
	}
>>>>>>> webops-ejb/src/main/java/entities/User.java
	
	
	
	


}
