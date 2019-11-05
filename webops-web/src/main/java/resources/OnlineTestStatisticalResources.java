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

import services.OnlineTestStatisticalImplementation;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("OnlineTestStatisticalResources")
public class OnlineTestStatisticalResources {
	@Inject
	OnlineTestStatisticalImplementation OTSI;

	
	@GET
	@Path("/AcceptedTestPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AcceptedTestPerYear(@QueryParam("year") int year) {
		float x = OTSI.AcceptedTestPerYear(year);
		return Response.status(Status.OK).entity("percent of Accepted Test for " + year + " =  " + x + "%").build();
	}

	
	@GET
	@Path("/RejectedTestPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response RejectedTestPerYear(@QueryParam("year") int year) {
		float x = OTSI.RejectedTestPerYear(year);
		return Response.status(Status.OK).entity("percent of Rejected Test for " + year + " =  " + x + "%").build();
	}

	
	@GET
	@Path("/NbTestPerYear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response NbTestPerYear(@QueryParam("year") int year) {
		int x = OTSI.NbTestPerYear(year);
		return Response.status(Status.OK).entity("Number of Test for " + year + " =  " + x + "").build();
	}

		
	@GET
	@Path("/NbTestPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response NbTestPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		int x = OTSI.NbTestPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("Number  of Test for " + year + " and month " + month + " =  " + x + "").build();
	}

	
	@GET
	@Path("/AcceptedTestPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AcceptedTestPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		float x = OTSI.AcceptedTestPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("percent of Accepted Test for " + year + "and month " + month + " =  " + x + "%").build();
	}

	@GET
	@Path("/RejectedTestPerMonth")
	@Produces(MediaType.TEXT_PLAIN)
	public Response RejectedTestPerMonth(@QueryParam("year") int year, @QueryParam("month") int month) {
		float x = OTSI.RejectedTestPerMonth(year, month);
		return Response.status(Status.OK)
				.entity("percent of Rejected Test for " + year + "and month " + month + " =  " + x + "%").build();
	}

}
