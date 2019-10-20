package resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Employe;
import services.EmployeeServiceImpl;

@RequestScoped
@Path("employee")
public class EmployeeService {

	@Inject
	EmployeeServiceImpl empService;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerEmployee(Employe emp)
	{
		int idEmp = empService.addEmployee(emp);
		if(idEmp != 0)
		{
			return Response.status(Status.CREATED).entity("Registeration Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed ! Email already exists ").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllUsers()
	{
		return Response.status(Status.ACCEPTED).entity("Test").build();
	}
}
