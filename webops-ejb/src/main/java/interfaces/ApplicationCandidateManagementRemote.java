package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.Application;
import entities.ApplicationId;
@Remote
public interface ApplicationCandidateManagementRemote {
	public Set<Application> ViewAllApplicationStillWait();
	public Set<Application> ViewAllApplicationAccepted();
	public Set<Application> ViewAllApplicationRejected();
	public int AcceptApplication(ApplicationId id);
	public int RejectApplication(ApplicationId id);
	
}
