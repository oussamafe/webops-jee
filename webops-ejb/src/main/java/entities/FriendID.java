package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable

public class FriendID  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int FriendID;
    private int CandidateId;
	public int getFriendID() {
		return FriendID;
	}
	public void setFriendID(int friendID) {
		FriendID = friendID;
	}
	public int getCandidateId() {
		return CandidateId;
	}
	public void setCandidateId(int candidateId) {
		CandidateId = candidateId;
	}
    

}
