package resources;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

@RequestScoped
@Path("Candidate")
public class CandidateResources {
	@Inject
	CandidateService candidateservice;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegisterCandidate(Candidate C)
	{
		int idC = candidateservice.addCandidate(C);
		if(idC != 0)
		{
			return Response.status(Status.CREATED).entity("Registeration Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed :( Email already exists ").build();
	}
	
	@PUT
	
	public Response UpdateEmailCandidate(@QueryParam("Email")String Email,@QueryParam("id") int idCandidate)
	{	
		int idC=candidateservice.UpdateEmailCandidate(idCandidate,Email);
		if(idC == 1)
		{
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
	public Response updateCourse(Candidate	 C) {
		candidateservice.UpdateCandidate(C);
	return Response.status(Status.ACCEPTED).entity("Successful Update").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AffectCourseCandidate")
	public Response affectCourseCandidat(@QueryParam("idC") int idCourse , @QueryParam("idCand") int idCandidate)
	{
		candidateservice.affectCourseCandidate(idCourse, idCandidate);
		return Response.status(Status.ACCEPTED).entity("Add Successful").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AffectProfExpCandidate")
	public Response affectProfExpCandidat(@QueryParam("idProf") int idProfExp , @QueryParam("idCan") int idCandidate)
	{
		candidateservice.affectProExpCandidate(idProfExp, idCandidate);
		return Response.status(Status.ACCEPTED).entity("Add Successful").build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllCourses")
	public Response getAllCourseBycandidate( @QueryParam("idCan") int idCandidate)
	{
		Set<Course> E =candidateservice.getAllCourseBycandidate(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}

}
