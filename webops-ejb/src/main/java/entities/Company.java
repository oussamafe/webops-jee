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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String field;
	private int nbEmployees;
	

	//@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL},mappedBy="company")
	private List<Employe> employes = new ArrayList<Employe>();
	
	public Company(String name, String field, int nbEmployees) {
		super();
		this.name = name;
		this.field = field;
		this.nbEmployees = nbEmployees;
	}

	public Company() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getNbEmployees() {
		return nbEmployees;
	}

	public void setNbEmployees(int nbEmployees) {
		this.nbEmployees = nbEmployees;
	}

	public List<Employe> getEmployees() {
		return employes;
	}

	public void setEmployees(List<Employe> employees) {
		this.employes = employees;
	}}


	
	
	