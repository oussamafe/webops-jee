package resources;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Candidate;
import entities.Course;
import services.CandidateService;
import utilites.JavaMail;

@RequestScoped
@Path("Candidate")
public class CandidateResources {
	@Inject
	CandidateService candidateservice;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegisterCandidate(Candidate C) throws MessagingException
	{
		int idC = candidateservice.addCandidate(C);
		if(idC != 0)
		{	JavaMail.sendConfirmationAcount(C.getEmail());
			return Response.status(Status.CREATED).entity(C.getEmail()).build();
			
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed :( Email already exists ").build();
	}

	@PUT
	public Response UpdateEmailCandidate(@QueryParam("Email")String Email,@QueryParam("id") int idCandidate) throws MessagingException
	{	
		int idC=candidateservice.UpdateEmailCandidate(idCandidate,Email);
		if(idC == 1)
		{	JavaMail.sendMessageforupdate(Email);
			return Response.status(Status.OK).entity("Email changed Successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Email Failed :( Email already exists ").build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayCandidateDetails(@QueryParam("id") int idCandidate)
	{
		return Response.status(Status.ACCEPTED).entity(candidateservice.displayCandidateDetails(idCandidate)).build();
	}


	@PUT
	@Path("updateCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCandidate(Candidate	C) {
		candidateservice.UpdateCandidate(C);
		return Response.status(Status.ACCEPTED).entity("Successful Update").build();
	}
	@GET
	@Path("RechercheCantact")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RechercheCantact(@QueryParam("skill") String skill)
	{
		return Response.status(Status.ACCEPTED).entity(candidateservice.SearchCandidateSkills(skill)).build();
	}
	@GET
	@Path("RechercheCompany")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Recherchecompany(@QueryParam("Search") String str)
	{
		return Response.status(Status.ACCEPTED).entity(candidateservice.SearchCompanyByNameField(str)).build();
	}
	@GET
	@Path("RechercheJobOffer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RechercheJobOffer(@QueryParam("Search") String str)
	{
		return Response.status(Status.ACCEPTED).entity(candidateservice.SearchJobOfferMultipe(str)).build();
	}
}
