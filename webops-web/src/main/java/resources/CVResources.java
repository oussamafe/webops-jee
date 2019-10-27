package resources;


import java.util.Set;

import javax.enterprise.context.RequestScoped;
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

import entities.Candidate;
import entities.Course;
import entities.ProfessionalExperience;
import services.CVService;
import services.CandidateService;


@Path("CV")
@RequestScoped
public class CVResources {
	@Inject
	CVService CVServices;
	@Inject
	CandidateService candidateservice;
	
	@POST
	@Path("Course")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddCourse(Course C,@QueryParam("id") int idCandidate)
	{	
		int idCourse = CVServices.addCourse(C);
		candidateservice.affectCourseCandidate(idCourse, idCandidate);
		if(idCourse != 0)
		{	
			return Response.status(Status.CREATED).entity("Course added Successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Course add Failed :( ").build();
	}

	@POST
	@Path("ProfessionalExperience")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExpPro(ProfessionalExperience P,@QueryParam("id") int idCandidate)
	{
		int idExpPro = CVServices.addExpPro(P);
		candidateservice.affectProExpCandidate(idExpPro, idCandidate);
		if(idExpPro != 0)
		{
			return Response.status(Status.CREATED).entity("Professional Experience added Successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Professional Experience add Failed :( ").build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("DeleteCourse")
	public Response RemoveCourse(@QueryParam("id") int id,@QueryParam("idC") int idC)
	{	
		CVServices.RemoveCourse(idC,id);

	return Response.status(Status.ACCEPTED).entity("Course Deleted").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("DeleteProfExp")
	public Response RemoveProfExp(@QueryParam("id") int id,@QueryParam("idC") int idC)
	{	
		CVServices.RemoveExpPro(id,idC);

	return Response.status(Status.ACCEPTED).entity("Professional Experience Deleted").build();
	}
	

	@PUT
	@Path("updateCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourse(Course	 C) {
		CVServices.updatecourse(C);
	return Response.status(Status.ACCEPTED).entity("Successful Update").build();
	}
	
	@PUT
	@Path("updateExpPro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProfExp(ProfessionalExperience P) {
		CVServices.updateExpPro(P);
	return Response.status(Status.ACCEPTED).entity("Successful Update").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllCourses")
	public Response getAllCourseBycandidate( @QueryParam("idCan") int idCandidate)
	{
		Set<Course> E =candidateservice.getAllCourseBycandidate(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	//not tested yet
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllProfessionalExp")
	public Response getAllProfExpBycandidate( @QueryParam("idCan") int idCandidate)
	{
		Set<ProfessionalExperience> E =candidateservice.getAllExpProdBycandidate(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	

}
