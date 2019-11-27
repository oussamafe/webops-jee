package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.faces.bean.RequestScoped;
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

import entities.AvailabilityCandidate;
import entities.AvailabilityEmploye;
import entities.Interview;
import entities.Role;
import services.AvailabilityImplementation;
import utilites.Roles;
import utilites.RolesAllowed;

@RequestScoped
@Path("AvailabilityResources")
public class AvailabilityResources {
	
	@Inject
	AvailabilityImplementation AI;
	
	
	@PUT
	@Path("/SetAvailibleCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SetAvailibleCandidate(@QueryParam(value = "cid") int candidateID,@QueryParam(value = "dateav") String dateav,@QueryParam(value = "heurdeb") int heurDebut) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(dateav);
			AI.SetAvailibleCandidate(candidateID, date, heurDebut);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/SetAvailibleEmploye")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SetAvailibleEmploye(@QueryParam(value = "eid") int EmployeID,@QueryParam(value = "dateav") String dateav,@QueryParam(value = "heurdeb") int heurDebut) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(dateav);
		AI.SetAvailibleEmploye(EmployeID, date, heurDebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/SetNoAvailibleCandidate")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response SetNoAvailibleCandidate(@QueryParam(value = "cid") int candidateID,@QueryParam(value = "dateav") String dateav,@QueryParam(value = "heurdeb") int heurDebut) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(dateav);
		AI.SetNoAvailibleCandidate(candidateID, date, heurDebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("/SetNoAvailibleEmploye")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SetNoAvailibleEmploye(@QueryParam(value = "eid") int EmployeID,@QueryParam(value = "dateav") String dateav,@QueryParam(value = "heurdeb") int heurDebut) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(dateav);
		AI.SetNoAvailibleEmploye(EmployeID, date, heurDebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}
	
	
	
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
