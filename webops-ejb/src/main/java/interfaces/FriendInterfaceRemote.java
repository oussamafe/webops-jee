package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Candidate;
import entities.Friend;
@Remote
public interface FriendInterfaceRemote {
	public void sendFriendRequest(Friend F,int idCandidate1,int idCandidate2,int idSender);
	public void AcceptFriendRequest(int idCandidate1,int idCandidate2);
	public void RejectFriendRequest(int idCandidate1,int idCandidate2);
	public void RemoveFriend(int idCandidate,int idFriend);
	List<Candidate>getAllMyFriends(int Candidate);
	List<Candidate> getAllMyFriendRequest(int Candidate);
}
