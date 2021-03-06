package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Interview;
import entities.InterviewType;
import services.InterviewManagementImplimentation;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("InterviewResources")
public class InterviewResources {

	@Inject
	InterviewManagementImplimentation IMI;

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@POST
	@Path("/AddInterview")
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response AddInterview(@QueryParam(value = "cid") int candidateID) {

		IMI.AddInterview(candidateID);
		return Response.status(Status.CREATED).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@POST
	@Path("/AddInterviewType")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddInterviewType(InterviewType interviewType) {
		int x = IMI.AddInterviewType(interviewType);
		return Response.status(Status.CREATED).entity("add interview type with id : "+x).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/UpdateInterview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateInterview(@QueryParam(value = "iid") int interviewID, Interview interview) {
		IMI.UpdateInterview(interviewID, interview);
		return Response.status(Status.OK).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/SetInValidInterview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SetInValidInterview(@QueryParam(value = "iid") int interviewID) {
		IMI.SetInValidInterview(interviewID);
		return Response.status(Status.OK).entity("interview ==> InValid").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/SetValidInterview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SetValidInterview(@QueryParam(value = "iid") int interviewID) {
		IMI.SetValidInterview(interviewID);
		return Response.status(Status.OK).entity("interview ==> Valid").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/UpdateInterviewType")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateInterviewType(@QueryParam(value = "itid") int interviewTypeID, InterviewType interviewType) {
		IMI.UpdateInterviewType(interviewTypeID, interviewType);
		return Response.status(Status.OK).entity("update with succes").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@GET
	@Path("/ListAllInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAllInterview() {
		Set<Interview> list = IMI.ListAllInterview();
		return Response.status(Status.OK).entity(list).build();
	}

	
	
	
	
	
	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager})
	@GET
	@Path("/ListInterviewByCandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByCandidate(@QueryParam("cid") int candidateID) {
		Set<Interview> list = IMI.ListInterviewByCandidate(candidateID);
		return Response.status(Status.OK).entity(list).build();
	}

	
	
	
	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@GET
	@Path("/ListInterviewByEmploye")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByEmploye(@QueryParam(value = "eid") int employeID) {
		Set<Interview> list = IMI.ListInterviewByEmploye(employeID);
		return Response.status(Status.OK).entity(list).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@GET
	@Path("/ListInterviewPerDate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewPerDate(@QueryParam("date") String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Set<Interview> list = new HashSet<Interview>();
		Date datee;
		try {
			datee = df.parse(date);
			list = IMI.ListInterviewPerDate(datee);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(list).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@GET
	@Path("/ListInterviewByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListInterviewByType(@QueryParam("itid") int InterviewTypeID) {
		Set<Interview> list = IMI.ListInterviewByType(InterviewTypeID);
		return Response.status(Status.OK).entity(list).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@GET
	@Path("/ListAllInterviewType")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAllInterviewType() {
		Set<InterviewType> list = IMI.ListAllInterviewType();
		return Response.status(Status.OK).entity(list).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@DELETE
	@Path("/DeleteInterviewType")
	public Response DeleteInterviewType(@QueryParam(value = "itid") int interviewTypeID) {
		IMI.DeleteInterviewType(interviewTypeID);
		return Response.status(Status.CREATED).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@DELETE
	@Path("/DeleteInterview")
	public Response DeleteInterview(@QueryParam(value = "iid") int interviewID) {
		IMI.DeleteInterview(interviewID);
		return Response.status(Status.CREATED).build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/AffectInterviewToEmploye")
	public Response AffectInterviewToEmploye(@QueryParam(value = "iid") int interviewID,
			@QueryParam(value = "eid") int employeID) {
		IMI.AffectInterviewToEmploye(interviewID, employeID);

		return Response.status(Status.OK).build();

	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/AffectInterviewToCandidate")
	public Response AffectInterviewToCandidate(@QueryParam("iid") int interviewID,
			@QueryParam(value = "cid") int candidateID) {
		IMI.AffectInterviewToCandidate(interviewID, candidateID);

		return Response.status(Status.OK).build();

	}

	@RolesAllowed(Permissions = { Roles.Administrator, Roles.Human_Resources, Roles.Project_Manager })
	@PUT
	@Path("/AffectInterviewTypeToInterview")
	public Response AffectInterviewTypeToInterview(@QueryParam("iid") int interviewID,
			@QueryParam("itid") int interviewTypeID) {
		IMI.AffectInterviewTypeToInterview(interviewID, interviewTypeID);

		return Response.status(Status.OK).build();

	}

}
