package resources;

import java.util.List; 
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import entities.Reclamation; 
import services.ReclamationServiceImpl;

@RequestScoped
@Path("claims")
public class ReclamationService {
	@Inject
	ReclamationServiceImpl claims;

	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ReclamationUserToUser(Reclamation R)
	{ 
		if(R.getClaimtype().toString().equals("Service_Technique")) {
			claims.ClaimUserToAdmin(R);
			return Response.status(Status.CREATED).entity("Reclamation Envoyer en Success a l'admin \n"+R).build();}
		else if(R.getClaimtype().toString().equals("Company"))
		{
			if (R.getCompany()==null)
			{return Response.status(Status.BAD_REQUEST).entity("Veuillez choisir une company").build();}
			claims.ClaimUserToAdmin(R);

			return Response.status(Status.CREATED).entity("Reclamation Envoyer en Success au Company \n"+R).build();}
		else
			return Response.status(Status.NOT_ACCEPTABLE).entity("Veuillez preciser un Type 'Service_Technique' ou 'Company'").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ShowAllReclamation()
	{List<Reclamation> list=claims.ReadClaim();
	return Response.status(Status.ACCEPTED).entity(list).build();
	}

	@PUT 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReclamation(Reclamation R) {

		claims.UpdateClaim(R); 
		return Response.status(Status.ACCEPTED).entity("updated: "+R).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response DeleteReclamation(@PathParam("id")int id)
	{claims.DeleteClaim(id);

	return Response.status(Status.ACCEPTED).entity("deleted").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response ShowReclamationByUser(@PathParam("id")int id)
	{	List<Reclamation> list=claims.SuivieClaim(id); 
	return Response.status(Status.ACCEPTED).entity("Suivie Done \n "+list).build();
	}
}
