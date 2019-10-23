package entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/**
 * @author Gazzah
 *
 */
@DiscriminatorValue(value="Packs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Packs implements Serializable {
	//user has profile and profile has authorities
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String libelle;
	private String prix;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//id libelle, description, tarif, duree
}
