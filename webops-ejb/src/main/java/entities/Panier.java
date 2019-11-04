package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Gazzah
 *
 */
@DiscriminatorValue(value="Panier")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Panier {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	@ManyToOne
	private Packs packs;
	
	@ManyToOne
	private User userpanier;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Packs getPacks() {
		return packs;
	}
	public void setPacks(Packs packs) {
		this.packs = packs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
