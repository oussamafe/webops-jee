package interfaces;

import javax.ejb.Remote;

@Remote
public interface StatCandidateRemote {
	public Long UserNbrVistAccountbyCandidate(int idCandidate);
	public Long NbrApplicationDonebyCandidate(int idCandidate);
	public Long NbrApplicationAcceptedbyCandidate(int idCandidate);
	public Long NbrApplicationRefusedbyCandidate(int idCandidate);
	public Long NbrApplicationWaitingReplybyCandidate(int idCandidate);
	public Long nbrALlCandidate();
	public Long nbrApplicationAllSite();
	public Long nbrApplicationAllSitethisMonth();
	
}
