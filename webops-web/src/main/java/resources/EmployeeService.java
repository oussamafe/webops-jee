package resources;

<<<<<<< webops-web/src/main/java/resources/EmployeeService.java
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
=======
>>>>>>> webops-web/src/main/java/resources/EmployeeService.java
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
<<<<<<< webops-web/src/main/java/resources/EmployeeService.java
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
=======
>>>>>>> webops-web/src/main/java/resources/EmployeeService.java
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import entities.Company;
import entities.Employe;
<<<<<<< webops-web/src/main/java/resources/EmployeeService.java
import entities.Role;
import services.CompanyServiceImpl;
import services.EmployeeServiceImpl;
import utilites.LoggedInUser;
import utilites.Roles;
import utilites.RolesAllowed;
=======
import services.EmployeeServiceImpl;
>>>>>>> webops-web/src/main/java/resources/EmployeeService.java

@RequestScoped
@Path("employee")
public class EmployeeService {

<<<<<<< webops-web/src/main/java/resources/EmployeeService.java
	private static final String UPLOAD_FOLDER = "C:/Users/Oussama Fezzani/git/webops/webops-ejb/src/main/resources/images/";
	
	@EJB
	EmployeeServiceImpl empService;

	@EJB
	CompanyServiceImpl companyService;

	@Context
	private HttpHeaders headers;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerAdmin(Employe emp) {

		System.out.println("resources : "+emp.toString());
		if (emp.getRole().equals(Role.Administrator)) {
			empService.registerAdmin(emp);
=======
	@Inject
	EmployeeServiceImpl empService;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerEmployee(Employe emp)
	{
		int idEmp = empService.addEmployee(emp);
		if(idEmp != 0)
		{
>>>>>>> webops-web/src/main/java/resources/EmployeeService.java
			return Response.status(Status.CREATED).entity("Registration Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed ! Email already exists ").build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idE}/{token}")
	public Response activateAccount(@PathParam("idE") int idE, @PathParam("token") String token) {
		if (empService.activateAccount(idE, token)) {
			return Response.status(Status.CREATED).entity("Account Activated Successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Wrong Confirmation Code , Check your email !").build();
	}

	@POST
	@Path("company")
	@Consumes(MediaType.APPLICATION_JSON)
<<<<<<< webops-web/src/main/java/resources/EmployeeService.java
	@Path("register/{idC}")
	public Response registerEmployee(Employe employe, @PathParam("idC") int idC) {
		int idE = empService.registerEmployee(employe, idC);
		if (idE != 0) {
			return Response.status(Status.CREATED).entity("Account Created Successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}

	@RolesAllowed(Permissions = { Roles.Administrator })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("register/company")
	public Response registerCompany(Company company) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		Company com = empService.registerCompany(company, idA);
		if (com != null) {
			String res = empService.getIndexResult(com);
			if(res.equals("CREATED"))
				return Response.status(Status.CREATED).entity(res).build();
			else
			{
				return Response.status(Status.NOT_ACCEPTABLE).entity("NOT INDEXED !").build();
			}

		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager , Roles.Team_Leader})
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("edit/employee")
	public Response updateEmployee(Employe employe) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		
		int idC = empService.editEmployee(employe, idA);
		if (idC != 0) {
			return Response.status(Status.CREATED).entity("Edit done !").build();

		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager , Roles.Team_Leader})
	@DELETE
	@Path("edit/employee")
	public Response deleteEmployee()
	{
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		empService.removeEmployee(idA);
		return Response.status(Status.OK).entity("Delete done !").build(); 
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator})
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("edit/company")
	public Response editCompany(Company company)
	{
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		empService.editCompany(idA, company);
		return Response.status(Status.OK).entity("Edit done !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator})
	@DELETE
	@Path("edit/company")
	public Response removeCompany()
	{
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idA = logUser.getLoggedinUser(authorizationHeader);
		empService.removeCompany(idA);
		return Response.status(Status.OK).entity("Delete done !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager , Roles.Team_Leader})
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show/company")
	public Response showCompanyDetails()
	{
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idE = logUser.getLoggedinUser(authorizationHeader);
		Company company = empService.showCompanyDetails(idE);
		if(company != null)
		{
			return Response.status(Status.FOUND).entity(company).build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager , Roles.Team_Leader})
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show/employee")
	public Response showEmployeeDetails()
	{
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idE = logUser.getLoggedinUser(authorizationHeader);
		Employe employe = empService.showEmployeDetails(idE);
		if(employe != null)
		{
			return Response.status(Status.FOUND).entity(employe).build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Error !").build();
	}
	
	@RolesAllowed(Permissions = {Roles.Administrator})
	@POST
	@Consumes("multipart/form-data")
	@Path("company/edit/image")
	public Response addCompanyImage(MultipartFormDataInput form) throws IOException {

		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		LoggedInUser logUser = new LoggedInUser();
		int idE = logUser.getLoggedinUser(authorizationHeader);
		String uid = RandomStringUtils.random(8, false, true);
		InputStream uploadedInputStream = form.getFormDataPart("image", InputStream.class, null);
		if (uploadedInputStream == null)
			return Response.status(400).entity("Invalid form data").build();
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500).entity("Can not create destination folder on server").build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + uid + ".jpg";
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}
		empService.addImageCompany(idE, uid + ".jpg");
		return Response.status(200).entity("Success").build();
=======
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
>>>>>>> webops-web/src/main/java/resources/EmployeeService.java
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("emp/{idC}")
	public Response showCompanyEmployees(@PathParam("idC") int idC)
	{
		
		return Response.status(Status.ACCEPTED).entity(empService.showCompanyEmployees(idC)).build();
	}
	
	private void saveToFile(InputStream inStream, String target) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	private void createFolderIfNotExists(String dirName) throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
	
	
	

}
