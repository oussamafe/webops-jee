package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.JobOffer;
import services.JobOfferServiceImpl;
import utilites.LoggedInUser;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("joboffers")
public class JobOffersResources {

	@EJB
	JobOfferServiceImpl jobOffersService;

	@Context
	private HttpHeaders headers;

	@RolesAllowed(Permissions = { Roles.Human_Resources, Roles.Team_Leader })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addJobOffer(JobOffer jobOffer) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		JobOffer job = jobOffersService.addJobOffer(idA, jobOffer);
		if (job != null) {
			jobOffersService.indexJobOffer(job);
			return Response.status(Status.CREATED).entity("Job Offer added Successfully !").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}

	@RolesAllowed(Permissions = { Roles.Human_Resources })
	@GET
	@Path("approve")
	public Response approveJobOffer(@QueryParam(value = "idJ") int idJ, @QueryParam(value = "details") String details,
			@QueryParam(value = "approved") boolean approved) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		if (idJ != 0 && details != null && (approved == true || approved == false)) {

			int idJob = jobOffersService.approveJobOffer(idJ, details, approved);
			if (idJob != 0) {
				return Response.status(Status.CREATED).entity("Job Offer approval status changed !").build();
			}
			return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}

	@RolesAllowed(Permissions = { Roles.Human_Resources, Roles.Team_Leader })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show")
	public Response showJobOfferDetails(@QueryParam(value = "by") String type , @QueryParam(value = "job") int idJob) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);

		if (type != null) {
			if (type.equals("comapny")) {
				return Response.status(Status.FOUND).entity(jobOffersService.getJobOffersByCompany(idA)).build();
			} else if (type.equals("employee")) {
				return Response.status(Status.FOUND).entity(jobOffersService.getJobOffersByEmployee(idA)).build();
			}
			else if(type.equals("details") && idJob != 0)
			{
				return Response.status(Status.FOUND).entity(jobOffersService.getJobOfferDetails(idJob)).build();
			}
			return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = { Roles.Human_Resources })
	@DELETE
	@Path("remove/{idJ}")
	public Response removeJobOffer(@PathParam("idJ") int idJobOffer)
	{
		if(idJobOffer != 0)
		{
			jobOffersService.removeJobOffer(idJobOffer);
			return Response.status(Status.OK).entity("Deleted").build();
			
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = { Roles.Human_Resources })
	@PUT
	@Path("edit/{idJob}")
	public Response editJobOffer(JobOffer joboffer , @PathParam("idJob") int idJobOffer)
	{
		if(joboffer != null)
		{
			jobOffersService.editJobOffer(idJobOffer, joboffer);
			return Response.status(Status.OK).entity("Edited").build();
		}
		return null;
	}
	
	@RolesAllowed(Permissions = { Roles.Human_Resources })
	@GET
	@Path("available/{idJob}")
	public Response removeAvailability(@PathParam("idJob") int idJobOffer)
	{
		jobOffersService.removeAvailability(idJobOffer);
		return Response.status(Status.OK).entity("Edited").build();
	}

}
