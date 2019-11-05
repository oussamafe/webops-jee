package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface StatCandidateRemote {

	public Long NbrApplicationDonebyCandidate(int idCandidate);
	public Long NbrApplicationAcceptedbyCandidate(int idCandidate);
	public Long NbrApplicationRefusedbyCandidate(int idCandidate);
	public Long NbrApplicationWaitingReplybyCandidate(int idCandidate);
	public Long nbrALlCandidate();
	public Long nbrApplicationAllSite();
	public Long nbrApplicationAllSitethisMonth();
	public Long nbrJobOfferSkills(String skill);
	public Long nbrCandidateSkills(String skill);
	
}
