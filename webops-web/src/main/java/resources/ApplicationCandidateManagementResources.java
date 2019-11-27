package resources;

import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Application;
import entities.ApplicationId;
import services.ApplicationCandidateManagementImplementation;
import services.OnlineTestImplementation;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("ApplicationResources")
public class ApplicationCandidateManagementResources {
	@Inject
	ApplicationCandidateManagementImplementation ACMI;
	
	
	/*
	@POST
	@Path("/AddJson")
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRendezVous(JsonObject app) {
		
		//ACMI.ajouterApplication(app.getJobOffer().getId(), app.getCandidate().getId(), app.getDepositDate(), app.getAnswerDate());
		ACMI.ajouterApplication(app.getJsonObject("id").getInt("idJobOffer"),
								app.getJsonObject("id").getInt("idJobOffer"),
								Date.valueOf(app.getString("depositDate")),
								Date.valueOf(app.getString("answerDate")));
				return Response.status(Status.CREATED).entity("Ajout avec succ").build();
		
	}
	
	
	{
        "id": {
            "idJobOffer": 2,
            "idCandiate": 2
        },
        "depositDate": "2019-10-1",
        "answerDate": "2019-10-2",
        "result": true
}
	*/
	
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@GET
	@Path("/ApplicationStillWait")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response ViewAllApplicationStillWait() {
		Set<Application> list=ACMI.ViewAllApplicationStillWait();		
       return Response.status(Status.OK).entity(list).build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@GET
	@Path("/ApplicationAccepted")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response ViewAllApplicationAccepted()
	{
		Set<Application> list=ACMI.ViewAllApplicationAccepted();
		return Response.status(Status.OK).entity(list).build();
	}
	
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@GET
	@Path("/ApplicationRejected")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response ViewAllApplicationRejected()
	{
		Set<Application> list=ACMI.ViewAllApplicationRejected();
		return Response.status(Status.OK).entity(list).build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@PUT
	@Path("/acceptApplication")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ApplicationAccpet(ApplicationId id)
	{
		
		
		return Response.status(Status.OK).entity("Application with id =["+ACMI.AcceptApplication(id)+"] Accepted").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@PUT
	@Path("/RejectApplication")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ApplicationReject(ApplicationId id)
	{
		return Response.status(Status.OK).entity("Application with id =["+ACMI.RejectApplication(id)+"] Rejected").build();
	}
}
