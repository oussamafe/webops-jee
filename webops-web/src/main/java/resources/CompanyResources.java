package resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Company;
import services.CompanyServiceImpl;

@RequestScoped
@Path("company")
public class CompanyResources {

	private static final String UPLOAD_FOLDER = "C:/Users/Oussama Fezzani/git/webops/webops-ejb/src/main/resources/images/";

	@Context
	private UriInfo context;

	@EJB
	CompanyServiceImpl companyService;

	public CompanyResources() {
	}

	/*@POST
	@Consumes("multipart/form-data")
	public Response create(MultipartFormDataInput form) throws IOException {

		String uid = RandomStringUtils.random(8, false, true);
		InputStream uploadedInputStream = form.getFormDataPart("image", InputStream.class, null);
		Company company = form.getFormDataPart("company", Company.class,  Company.class);
		if (uploadedInputStream == null || company == null)
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
		company.setImage(uid + ".jpg");
		companyService.registerCompany(company);
		return Response.status(200).entity("Success").build();
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
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies()
	{
		return Response.status(Status.OK).entity(companyService.getAllCompanies()).build();
	}
}
