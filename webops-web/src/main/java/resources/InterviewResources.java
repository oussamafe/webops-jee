package resources;

import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Interview;
import entities.InterviewType;
import entities.Responce;
import entities.StateTestOnline;
import services.InterviewManagementImplimentation;

@RequestScoped
@Path("InterviewResources")
public class InterviewResources {

	@Inject
	InterviewManagementImplimentation IMI;

	@POST
	@Path("/AddInterview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddInterview(Interview interview) {
		int x = IMI.AddInterview(interview);
		return Response.status(Status.CREATED).entity(x).build();
	}

	@POST
	@Path("/AddInterviewType")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddInterviewType(InterviewType interviewType) {
		int x = IMI.AddInterviewType(interviewType);
		return Response.status(Status.CREATED).entity(x).build();
	}

	@PUT
	@Path("/UpdateInterview/{interviewID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateInterview(@PathParam(value = "interviewID") int interviewID, Interview interview) {
		IMI.UpdateInterview(interviewID, interview);
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("/UpdateInterviewType/{interviewTypeID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateInterviewType(@PathParam(value = "interviewTypeID") int interviewTypeID,
			InterviewType interviewType) {
		IMI.UpdateInterviewType(interviewTypeID, interviewType);
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("/ListAllInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAllInterview() {
		Set<Interview> list = IMI.ListAllInterview();
		return Response.status(Status.OK).entity(list).build();
	}

	@GET
	@Path("/ListInterviewByCandidate/{candidateID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByCandidate(@PathParam(value = "candidateID") int candidateID) {
		Set<Interview> list = IMI.ListInterviewByCandidate(candidateID);
		return Response.status(Status.OK).entity(list).build();
	}

	@GET
	@Path("/ListInterviewByEmploye/{employeID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByEmploye(@PathParam(value = "employeID") int employeID) {
		Set<Interview> list = IMI.ListInterviewByEmploye(employeID);
		return Response.status(Status.OK).entity(list).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("/ListInterviewPerDate/{date}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * ListInterviewPerDate(@PathParam(value = "date")Date date) { Set<Interview>
	 * list = IMI.ListInterviewPerDate(date); return
	 * Response.status(Status.OK).entity(list).build(); }
	 */

	@GET
	@Path("/ListInterviewByType/{InterviewTypeID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByType(@PathParam(value = "InterviewTypeID") int InterviewTypeID) {
		Set<Interview> list = IMI.ListInterviewByType(InterviewTypeID);
		return Response.status(Status.OK).entity(list).build();
	}

	@GET
	@Path("/ListAllInterviewType")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAllInterviewType() {
		Set<InterviewType> list = IMI.ListAllInterviewType();
		return Response.status(Status.OK).entity(list).build();
	}

	@DELETE
	@Path("/DeleteInterviewType/{interviewTypeID}")
	public Response DeleteInterviewType(@PathParam(value = "interviewTypeID") int interviewTypeID) {
		IMI.DeleteInterviewType(interviewTypeID);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("/DeleteInterview/{interviewID}")
	public Response DeleteInterview(@PathParam(value = "interviewID") int interviewID) {
		IMI.DeleteInterview(interviewID);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("/AffectInterviewToEmploye/{interviewID}/{employeID}")
	public Response AffectInterviewToEmploye(@PathParam(value = "interviewID") int interviewID,
			@PathParam(value = "employeID") int employeID) {
		IMI.AffectInterviewToEmploye(interviewID, employeID);

		return Response.status(Status.OK).build();

	}
	
	
	@PUT
	@Path("/AffectInterviewToCandidate/{interviewID}/{employeID}")
	public Response AffectInterviewToCandidate(@PathParam(value = "interviewID") int interviewID,
			@PathParam(value = "candidateID") int candidateID) {
		IMI.AffectInterviewToCandidate(interviewID, candidateID);

		return Response.status(Status.OK).build();

	}
	
	
	@PUT
	@Path("/AffectInterviewTypeToInterview/{interviewID}/{employeID}")
	public Response AffectInterviewTypeToInterview(@PathParam(value = "interviewID") int interviewID,
			@PathParam(value = "interviewTypeID") int interviewTypeID) {
		IMI.AffectInterviewTypeToInterview(interviewID, interviewTypeID);

		return Response.status(Status.OK).build();

	}

}
