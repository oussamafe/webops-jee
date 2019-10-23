package resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Company;
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
			return Response.status(Status.CREATED).entity("Registration Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed ! Email already exists ").build();
	}
	
	@POST
	@Path("company")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerCompany(Company company)
	{
		int idCompany = empService.addCompany(company);
		return Response.status(Status.CREATED).entity("Registration Successful").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllUsers()
	{
		return Response.status(Status.ACCEPTED).entity(empService.showCompanies()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idC}/{idE}")
	public Response addEmpToCompany(@PathParam("idC") int idC , @PathParam("idE") int idE)
	{
		empService.addEmployeeToCompany(idE, idC);
		return Response.status(Status.ACCEPTED).entity("Success").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("emp/{idC}")
	public Response showCompanyEmployees(@PathParam("idC") int idC)
	{
		
		return Response.status(Status.ACCEPTED).entity(empService.showCompanyEmployees(idC)).build();
	}
	
}
