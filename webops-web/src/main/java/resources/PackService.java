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
import entities.Packs;
import services.PackServiceImpl;


@RequestScoped
@Path("packs")
public class PackService {
	@Inject
	PackServiceImpl pack;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response PackCreation(Packs P)
	{
		pack.CreatePack(P);
		return Response.status(Status.CREATED).entity("Pack created Successful").build();
	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ShowAllPacks()
	{List<Packs> list=pack.ReadPack();
		return Response.status(Status.ACCEPTED).entity(list).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePacks(Packs P) {
		pack.UpdatePack(P);
	return Response.status(Status.ACCEPTED).entity("updated: "+P.getDescription()).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response DeletePacks(@PathParam("id")int id)
	{pack.DeletePack(id);
		
		return Response.status(Status.ACCEPTED).entity("deleted").build();
	}
}
