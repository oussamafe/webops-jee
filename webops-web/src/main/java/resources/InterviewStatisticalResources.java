package resources;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import services.InterviewStatisticalImpl;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("InterviewStatisticalResources")
public class InterviewStatisticalResources {

	@Inject
	InterviewStatisticalImpl ISI;

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/AcceptedInterviewPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AcceptedInterviewPerYear(@QueryParam("year") int year) {
		float x = ISI.AcceptedInterviewPerYear(year);
		return Response.status(Status.OK).entity("percent of Accepted Test for " + year + " =  " + x + "%").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/RejectedInterviewPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response RejectedInterviewPerYear(@QueryParam("year") int year) {
		float x = ISI.RejectedInterviewPerYear(year);
		return Response.status(Status.OK).entity("percent of Rejected Test for " + year + " =  " + x + "%").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/NbInterviewPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response NbInterviewPerYear(@QueryParam("year") int year) {
		int x = ISI.NbInterviewPerYear(year);
		return Response.status(Status.OK).entity("Number of Test for " + year + " =  " + x + "").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/NbInterviewPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response NbInterviewPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		int x = ISI.NbInterviewPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("Number  of Test for " + year + " and month " + month + " =  " + x + "").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/AcceptedInterviewPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AcceptedInterviewPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		float x = ISI.AcceptedInterviewPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("percent of Accepted Test for " + year + "and month " + month + " =  " + x + "%").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager, Roles.CANDIDATE,
			Roles.Team_Leader })
	@GET
	@Path("/RejectedInterviewPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response RejectedInterviewPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		float x = ISI.RejectedInterviewPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("percent of Rejected Test for " + year + "and month " + month + " =  " + x + "%").build();
	}

}
