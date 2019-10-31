package interfaces;


import java.util.Set;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface JobOffersInterf {

	public int addJobOffer(int idE , JobOffer jobOffer);
	public int approveJobOffer(int idJobOffer , String approvalDetails, boolean approved);
	public boolean editJobOffer(int idJobOffer , JobOffer jobOffer);
	public boolean removeJobOffer(int idJobOffer);
	public JobOffer getJobOfferDetails(int idJobOffer);
	public Set<JobOffer> getJobOffersByCompany(int idE);
	public Set<JobOffer> getJobOffersByEmployee(int idE);
	public void removeAvailability(int idJob);
}
