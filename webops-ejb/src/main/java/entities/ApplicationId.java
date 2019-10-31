package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApplicationId implements Serializable{


	@Column(name="idJobOffer")
	private int idJobOffer;
	
	@Column(name="idCandiate")
	private int idCandiate;

	public ApplicationId() {
		super();
	}

	public int getIdJobOffer() {
		return idJobOffer;
	}

	public void setIdJobOffer(int idJobOffer) {
		this.idJobOffer = idJobOffer;
	}

	public int getIdCandiate() {
		return idCandiate;
	}

	public void setIdCandiate(int idCandiate) {
		this.idCandiate = idCandiate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandiate;
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
		if (idCandiate != other.idCandiate)
			return false;
		if (idJobOffer != other.idJobOffer)
			return false;
		return true;
	}
	
	
	
}
