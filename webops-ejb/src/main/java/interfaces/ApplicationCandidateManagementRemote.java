package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Application;
@Remote
public interface ApplicationCandidateManagementRemote {
	public List<Application> ViewAllApplicationStillWait();
	public List<Application> ViewAllApplicationAccepted();
	public List<Application> ViewAllApplicationRejected();
	public Boolean AcceptApplication();
	public boolean RejectApplication();
	
}
