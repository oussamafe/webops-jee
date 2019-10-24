package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Company;
import entities.Employe;
import services.CompanyServiceImpl;
import services.EmployeeServiceImpl;
import utilites.CompanyData;
import utilites.SecuredAdministrator;
import utilites.SecuredCandidate;
import utilites.SecuredProjectManager;

@RequestScoped
@Path("employee")
public class EmployeeService {

	@EJB
	EmployeeServiceImpl empService;
	
	@EJB
	CompanyServiceImpl companyService;
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerCompany(CompanyData data) {
		
		if(data.getCompany() != null && data.getEmploye() != null)
		{
			int idC = companyService.registerCompany(data.company);
			if(idC != 0  && idC != -1)
			{
				int idE = empService.addAdmin(data.getEmploye(), idC);
				if(idE == -1)
				{
					companyService.removeComany(idC);
					return Response.status(Status.NOT_ACCEPTABLE).entity("Your Email Already Exists !").build();
					
				}
				return Response.status(Status.CREATED).entity("Registration Successful").build();
			}
			else if (idC == -1)
			{
				return Response.status(Status.NOT_ACCEPTABLE).entity("Your Company Already Exists !").build();
			}
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed !").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add/{idC}")
	public Response addEmployeeToCompany(Employe emp , @PathParam("idC") int idC)
	{
		int idE = empService.addEmployee(emp, idC);
		if(idE != 0 && idE != -1 )
		{
			return Response.status(Status.CREATED).entity("Registration Successful").build();
		}
		else if (idE == 0)
		{
			return Response.status(Status.CREATED).entity("Your Company already has a "+emp.getRole()).build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Your Email Already Exists !").build();
		
	}
	
	
	@SecuredProjectManager
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idE}")
	public Response showEmployeeDetails(@PathParam("idE") int idE)
	{
		Employe employe = empService.showEmployeeDetails(idE);
		if(employe != null)
		{
			return Response.status(Status.FOUND).entity(employe).build(); 
		}
		return Response.status(Status.NOT_FOUND).entity("The Employee your looking for doesn't exist !").build();
	}
	
	
	
}
