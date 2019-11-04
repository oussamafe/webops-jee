package resources;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Candidate;
import entities.Course;
import entities.Friend;
import services.CandidateService;
import services.FriendshipService;

@RequestScoped
@Path("Contacts")
public class ContactResources {
	@Inject
	CandidateService candidateservice;
	
	@EJB 
	FriendshipService friendshipservice;
	
	//Subscriptions and Subscribers Web Services
	
	@PUT
	@Path("CompanySub")
	public Response ToSubscribeToCompany(@QueryParam("idCand") int idCandidate,@QueryParam("idComp") int idCompany)
	{
		candidateservice.ToSubScribetoCompany(idCandidate, idCompany);
		return Response.status(Status.CREATED).entity("Subscription For this company done..! ").build();
		
	
	}
	
	@PUT
	@Path("CompanyRemoveSub")
	public Response ToRemoveCompanyeSub(@QueryParam("idCand") int idCandidate,@QueryParam("idComp") int idCompany)
	{
		candidateservice.ToRemoveCompanySub(idCandidate, idCompany);
		return Response.status(Status.CREATED).entity("Subscription Removed successfull..! ").build();
		
	
	}
	
	@PUT
	@Path("CandidateSub")
	public Response ToSubscribeToCandidate(@QueryParam("idCand") int idCandidate,@QueryParam("idSub") int idSub)
	{
		candidateservice.ToSubScribetoCandidate(idCandidate, idSub);
		return Response.status(Status.CREATED).entity("Subscription successfull..! ").build();
		
	
	}
	
	@PUT
	@Path("CandidateRemoveSub")
	public Response ToRemoveCandidateSub(@QueryParam("idCand") int idCandidate,@QueryParam("idSub") int idSub)
	{
		candidateservice.ToRemoveCandidateSub(idCandidate, idSub);
		return Response.status(Status.CREATED).entity("Subscription Removed successfull..! ").build();
		
	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllMySubscribers")
	public Response getAllMySubscribers( @QueryParam("idCan") int idCandidate)
	{
		List<String> E =candidateservice.getAllMysubscribers(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	//works
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllMySubscriptions")
	public Response getAllMySubscriptions( @QueryParam("idCan") int idCandidate)
	{
		List<String> E =candidateservice.getAllMyCandidateSubs(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	// i Don't have data on company table yet,So i didn't test it 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllCompanySubscriptions")
	public Response getAllMyCompanySubs( @QueryParam("idComp") int idCompany)
	{
		List<String> E =candidateservice.getAllMyCompanySub(idCompany);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	//Friends Managements
	
	@POST
	@Path("SendFriendRequest")
	public Response SendFriendRequest(@QueryParam("idSender") int idSender,@QueryParam("idReciever") int idReciever)
	{	Friend F=new Friend();
		friendshipservice.sendFriendRequest(F, idSender, idReciever,idSender);
		friendshipservice.sendFriendRequest(F, idReciever, idSender,idSender);
		return Response.status(Status.CREATED).entity("Request was sent successfully..! ").build();
		
	
	}
	
	@DELETE
	@Path("TraiteFriendRequest/Refuse")
	public Response RefuseFriendRequest(@QueryParam("idSender") int idSender,@QueryParam("idReciever") int idReciever)
	{
		friendshipservice.RejectFriendRequest(idSender, idReciever);
		friendshipservice.RejectFriendRequest(idReciever, idSender);
		return Response.status(Status.CREATED).entity("Request was Refused successfully..! ").build();
		
	
	}
	
	
	@PUT
	@Path("TraiteFriendRequest/Accept")
	public Response AcceptFriendRequest(@QueryParam("idSender") int idSender,@QueryParam("idReciever") int idReciever)
	{
		friendshipservice.AcceptFriendRequest(idSender, idReciever);
		friendshipservice.AcceptFriendRequest(idReciever, idSender);
		return Response.status(Status.CREATED).entity("Request was accepted successfully..! ").build();
		
	
	}
	
	@PUT
	@Path("RemoveFriend")
	public Response RemoveFriendRequest(@QueryParam("idCandidate") int idCandidate,@QueryParam("idFriend") int idFriend)
	{
		friendshipservice.RemoveFriend(idCandidate, idFriend);
		
		friendshipservice.RemoveFriend(idFriend, idCandidate);
		
		return Response.status(Status.CREATED).entity("Friend was removed successfully..! ").build();
		
	
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllMyFriends")
	public Response getAllMyFriends( @QueryParam("idCan") int idCandidate)
	{
		List<Candidate> E =friendshipservice.getAllMyFriends(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("AllMyFriendsRequests")
	public Response getAllMyFriendsRequests( @QueryParam("idCan") int idCandidate)
	{
List<Candidate> E =friendshipservice.getAllMyFriendRequest(idCandidate);
		
		return Response.status(Status.ACCEPTED).entity(E).build();
	}
}
