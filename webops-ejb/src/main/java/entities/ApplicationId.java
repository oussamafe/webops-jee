package entities;

import java.io.Serializable;

import javax.persistence.Column;
<<<<<<< webops-ejb/src/main/java/entities/ApplicationId.java
import javax.persistence.Embeddable;
=======
import javax.persistence.Embeddable; 
>>>>>>> webops-ejb/src/main/java/entities/ApplicationId.java

@Embeddable
public class ApplicationId implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="idJobOffer")
	private int idJobOffer;
	
	@Column(name="idCandidate")
	private int idCandidate;

	public int getIdJobOffer() {
		return idJobOffer;
	}

	public void setIdJobOffer(int idJobOffer) {
		this.idJobOffer = idJobOffer;
	}

	public int getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(int idCandidate) {
		this.idCandidate = idCandidate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandidate;
		result = prime * result + idJobOffer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationId other = (ApplicationId) obj;
		if (idCandidate != other.idCandidate)
			return false;
		if (idJobOffer != other.idJobOffer)
			return false;
		return true;
	}

	
	
	
}
