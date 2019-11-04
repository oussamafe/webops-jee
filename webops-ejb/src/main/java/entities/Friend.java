package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Friend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private FriendID id;

	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "FriendID")
	private Candidate Friend;
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name ="CandidateID")
	private Candidate Candidate;

	private boolean state ;

	@Temporal(TemporalType.DATE)
	private Date dateRequest;
	@Temporal(TemporalType.DATE)
	private Date dateFriendship;
	public int Sender; //0=sender --- 1/=Reciever
	public boolean isState() {
		return state;
	}
	
	

	public int getSender() {
		return Sender;
	}



	public void setSender(int sender) {
		Sender = sender;
	}



	public void setState(boolean state) {
		this.state = state;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Candidate == null) ? 0 : Candidate.hashCode());
		result = prime * result + ((Friend == null) ? 0 : Friend.hashCode());
		result = prime * result + ((dateFriendship == null) ? 0 : dateFriendship.hashCode());
		result = prime * result + ((dateRequest == null) ? 0 : dateRequest.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (state ? 1231 : 1237);
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
		Friend other = (Friend) obj;
		if (Candidate == null) {
			if (other.Candidate != null)
				return false;
		} else if (!Candidate.equals(other.Candidate))
			return false;
		if (Friend == null) {
			if (other.Friend != null)
				return false;
		} else if (!Friend.equals(other.Friend))
			return false;
		if (Sender != other.Sender)
			return false;
		if (dateFriendship == null) {
			if (other.dateFriendship != null)
				return false;
		} else if (!dateFriendship.equals(other.dateFriendship))
			return false;
		if (dateRequest == null) {
			if (other.dateRequest != null)
				return false;
		} else if (!dateRequest.equals(other.dateRequest))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	public Friend(FriendID id, entities.Candidate friend, entities.Candidate candidate, boolean state, Date dateRequest,
			Date dateFriendship, boolean sender) {
		super();
		this.id = id;
		Friend = friend;
		Candidate = candidate;
		this.state = state;
		this.dateRequest = dateRequest;
		this.dateFriendship = dateFriendship;
		
	}

	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDateRequest() {
		return dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}


	public FriendID getId() {
		return id;
	}

	public void setId(FriendID id) {
		this.id = id;
	}

	public Candidate getFriend() {
		return Friend;
	}

	public void setFriend(Candidate friend) {
		Friend = friend;
	}

	public Candidate getCandidate() {
		return Candidate;
	}

	public void setCandidate(Candidate candidate) {
		Candidate = candidate;
	}

	public Date getDateFriendship() {
		return dateFriendship;
	}

	public void setDateFriendship(Date dateFriendship) {
		this.dateFriendship = dateFriendship;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
