package resources;

import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;
import services.AvailabilityImplementation;

@RequestScoped
@Path("AvailabilityResources")
public class AvailabilityResources {
	
	@Inject
	AvailabilityImplementation AI;
	
	@POST
	@Path("/AddAvailabilityCandidate/{candidateID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddAvailabilityCandidate(@PathParam(value = "CandidateID")int candidateID,AvailabilityCandidate ac) {
		 AI.AddAvailabilityCandidate(candidateID,ac);
		return Response.status(Status.CREATED).build();
	}
	@POST
	@Path("/AddAvailabilityEmploye/{employeID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddAvailabilityEmploye(@PathParam(value = "employeID")int employeID,AvailabilityEmploye ae) {
		 AI.AddAvailabilityEmploye(employeID,ae);
		return Response.status(Status.CREATED).build();
	}

	
	@DELETE
	@Path("/DeleteAvailability/{availabilityID}")
	public Response DeleteAvailability(@PathParam(value = "availabilityID")int availabilityID) {
		 AI.DeleteAvailability(availabilityID);
		return Response.status(Status.CREATED).build();
	}
	
	
	@GET
	@Path("/ListAvailabilityCandidate/{candidateID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAvailabilityCandidate(@PathParam(value = "CandidateID")int candidateID) {
		Set<AvailabilityCandidate> list = AI.ListAvailabilityCandidate(candidateID);
		return Response.status(Status.OK).entity(list).build();
	}
	
	@GET
	@Path("/ListAvailabilityEmploye/{employeID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAvailabilityEmploye(@PathParam(value = "employeID")int employeID) {
		Set<AvailabilityEmploye> list = AI.ListAvailabilityEmploye(employeID);
		return Response.status(Status.OK).entity(list).build();
	}
}
