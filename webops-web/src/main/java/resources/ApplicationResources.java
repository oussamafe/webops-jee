package resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import entities.Application;
import services.ApplicationService;

@RequestScoped
@Path("Application")
public class ApplicationResources {
	
	@Inject
	ApplicationService APService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApp(Application A,@QueryParam("idC") int idCandidate, @QueryParam("idJ")int idJobOffer)
	{
		APService.addApplication(A, idCandidate, idJobOffer);
		return Response.status(Status.CREATED).entity("Application en attente....").build();
		
	}

	@GET
	@Path("DetailApplication")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayApplictionDetails(@QueryParam("idC") int idCandidate,@QueryParam("idJ") int idJobOffer)
	{
		return Response.status(Status.ACCEPTED).entity(APService.displayApplicationDetails(idCandidate, idJobOffer)).build();
	}

	@DELETE
	@Path("DeleteApplication")
	public Response RemoveApplication(@QueryParam("idC") int idCandidate,@QueryParam("idJ") int idJobOffer)
	{	
		APService.RemoveApplication(idCandidate, idJobOffer);

	return Response.status(Status.ACCEPTED).entity("Application Deleted").build();
	}
}