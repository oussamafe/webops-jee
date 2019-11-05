package services;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import entities.Employe;
import entities.JobOffer;
import entities.Role;
import entities.Skill;
import interfaces.JobOffersInterf;

@Stateless
@LocalBean
public class JobOfferServiceImpl implements JobOffersInterf {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	public JobOffer addJobOffer(int idE, JobOffer jobOffer) {

		Employe employe = em.find(Employe.class, idE);
		if (employe != null) {
			if (employe.getRole().equals(Role.Human_Resources)) {

				jobOffer.setCompany_offers(employe.getCompany());
				jobOffer.setApproved(true);
				jobOffer.setSubmittedBy(employe);
				jobOffer.setApprovalDate(new Date());
				jobOffer.setApprovalDetails(null);
				jobOffer.setDepositDate(new Date());
				for (Skill s : jobOffer.getSkills()) {

				}
				em.persist(jobOffer);
				return jobOffer;
			} else {
				jobOffer.setCompany_offers(employe.getCompany());
				jobOffer.setApproved(false);
				jobOffer.setSubmittedBy(employe);
				jobOffer.setApprovalDate(null);
				jobOffer.setApprovalDetails(null);
				jobOffer.setDepositDate(new Date());
				em.persist(jobOffer);
				return jobOffer;
			}
		}
		return null;
	}

	@Override
	public int approveJobOffer(int idJobOffer, String approvalDetails, boolean approved) {

		JobOffer jobOffer = em.find(JobOffer.class, idJobOffer);
		if (jobOffer != null) {
			jobOffer.setApproved(approved);
			jobOffer.setApprovalDate(new Date());
			jobOffer.setApprovalDetails(approvalDetails);
			return jobOffer.getId();

		}
		return 0;
	}

	@Override
	public boolean editJobOffer(int idJobOffer, JobOffer jobOffer) {
		JobOffer jobO = em.find(JobOffer.class, idJobOffer);
		if (jobO != null) {
			jobOffer.setId(jobO.getId());
			em.merge(jobOffer);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeJobOffer(int idJobOffer) {
		JobOffer jobO = em.find(JobOffer.class, idJobOffer);
		if (jobO != null) {
			em.remove(jobO);
			return true;
		}
		return false;
	}

	@Override
	public JobOffer getJobOfferDetails(int idJobOffer) {

		JobOffer jobO = em.find(JobOffer.class, idJobOffer);
		if (jobO != null) {
			return jobO;
		}
		return null;
	}

	@Override
	public Set<JobOffer> getJobOffersByCompany(int idE) {

		Employe employe = em.find(Employe.class, idE);
		if (employe != null) {
			return employe.getCompany().getComapnyJobs();
		}
		return null;
	}

	@Override
	public Set<JobOffer> getJobOffersByEmployee(int idE) {
		Employe employe = em.find(Employe.class, idE);
		if (employe != null) {
			return employe.getJobsSubmitted();
		}
		return null;
	}

	@Override
	public void removeAvailability(int idJob) {

		JobOffer job = em.find(JobOffer.class, idJob);
		if (job != null) {
			job.setAvailable(false);
		}

	}

	@Override
	public Skill checkSkillExistance(Skill skills) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String indexJobOffer(JobOffer jobOffer) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String offer = objectMapper.writeValueAsString(jobOffer);
			Settings settings = Settings.builder().put("client.transport.sniff", true)
					.put("cluster.name", "elasticsearch").build();
			TransportClient client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
			IndexResponse response = client.prepareIndex("joboffers", "_doc", String.valueOf(jobOffer.getId()))
					.setSource(offer, XContentType.JSON).get();
			client.close();
		} catch (JsonProcessingException e) {
		}
		return null;
	}

}
