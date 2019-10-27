package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.Application;
import entities.ApplicationId;

@Remote
public interface ApplicationCandidateManagementRemote {
	public Set<Application> ViewAllApplicationStillWait();// tested ok

	public Set<Application> ViewAllApplicationAccepted();// tested ok

	public Set<Application> ViewAllApplicationRejected();// tested ok

	public int AcceptApplication(ApplicationId id);// tested ok

	public int RejectApplication(ApplicationId id);// tested ok

}
