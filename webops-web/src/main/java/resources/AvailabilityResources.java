package resources;

import java.util.Set;

import javax.faces.bean.RequestScoped;
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

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;
import services.AvailabilityImplementation;

@RequestScoped
@Path("AvailabilityResources")
public class AvailabilityResources {
	
	@Inject
	AvailabilityImplementation AI;
	/*
	@POST
	@Path("/AddAvailabilityCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddAvailabilityCandidate(@QueryParam(value = "candidateID")int candidateID,AvailabilityCandidate ac) {
		 AI.AddAvailabilityCandidate(candidateID,ac);
		return Response.status(Status.CREATED).build();
	}
	@POST
	@Path("/AddAvailabilityEmploye")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddAvailabilityEmploye(@QueryParam(value = "employeID")int employeID,AvailabilityEmploye ae) {
		 AI.AddAvailabilityEmploye(employeID,ae);
		return Response.status(Status.CREATED).build();
	}

	
	@DELETE
	@Path("/DeleteAvailability")
	public Response DeleteAvailability(@QueryParam(value = "availabilityID")int availabilityID) {
		 AI.DeleteAvailability(availabilityID);
		return Response.status(Status.OK).entity("Availability removed").build();
	}
	
	*/
	
	@POST
	@Path("/InitialiseCandidateAvailability")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response InitialiseCandidateAvailability(@QueryParam(value = "cid")int candidateID) {
		 AI.InitialiseCandidateAvailability(candidateID);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Path("/InitialiseEmployeAvailability")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response InitialiseEmployeAvailability(@QueryParam(value = "eid")int EmployeID) {
		 AI.InitialiseEmployeAvailability(EmployeID);
		return Response.status(Status.CREATED).build();
	}
	
	
	
	@GET
	@Path("/ListAvailabilityCandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAvailabilityCandidate(@QueryParam(value = "cid")int candidateID) {
		Set<AvailabilityCandidate> list = AI.ListAvailabilityCandidate(candidateID);
		return Response.status(Status.OK).entity(list).build();
	}
	
	@GET
	@Path("/ListAvailabilityEmploye")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAvailabilityEmploye(@QueryParam(value = "eid")int employeID) {
		Set<AvailabilityEmploye> list = AI.ListAvailabilityEmploye(employeID);
		return Response.status(Status.OK).entity(list).build();
	}
}
