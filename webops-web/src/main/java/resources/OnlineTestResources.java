package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
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
import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
import services.OnlineTestImplementation;
import utilites.Roles;
import utilites.RolesAllowed;





@RequestScoped
@Path("OnlineTestResources")
public class OnlineTestResources {
	@Inject
	OnlineTestImplementation OTI;

	@RolesAllowed(Permissions = {Roles.Administrator , Roles.Human_Resources , Roles.Project_Manager})
	@POST
	@Path("/AddOnlineTest")
	public Response AddOnlineTest() {
		int x = OTI.AddOnlineTest();
		return Response.status(Status.CREATED).entity("add test with id = ["+x+"]   succes").build();
	}

	@POST
	@Path("/addTestQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestion(Question q) {
		OTI.addQuestion(q);
		return Response.status(Status.CREATED).entity("question with id["+q.getId()+"] added with succes").build();
	}

	
	
	/*
	@POST
	@Path("/addQuestionResponce")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResponce(Responce r) {
		OTI.addResponce(r);
		return Response.status(Status.CREATED).build();
	}
	*/
	@PUT
	@Path("/AddRepAndAffectToQuestion")
	public Response AffectResponceToQuestion(@QueryParam(value = "qid") int QuestionID,Responce r) {
		int rid=OTI.addResponce(r);
		OTI.AffectResponceToQuestion(rid, QuestionID);

		return Response.status(Status.OK).entity("Responce with id:["+rid+"] Affected To a Question  with Id:["+QuestionID+"]").build();

	}
	
	
	
	
	

	@PUT
	@Path("/affectCandidateTest")
	public Response affectTestToAnCandidate(@QueryParam(value = "cid") int CandidateID,
			@QueryParam(value = "otid") int TestID,@QueryParam(value = "datet") String datet) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(datet);
			OTI.affectTestToAnCandidate(CandidateID, TestID,date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

		return Response.status(Status.OK).entity("Candidate with id:["+CandidateID+"] Affected To An Online Test with Id:["+TestID+"]").build();

	}

	@PUT
	@Path("/affectQuestionTest")
	public Response AffectQuestionToAnOnlineTest(@QueryParam(value = "otid") int TestID,
			@QueryParam(value = "qid") int QuestionID) {
		OTI.AffectQuestionToAnOnlineTest(TestID, QuestionID);

		return Response.status(Status.OK).entity("Question with id:["+QuestionID+"] Affected To An Online Test with Id:["+TestID+"]").build();

	}

	

	
	@GET
	@Path("/ListQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestion() {
		Set<Question> list = OTI.ListQuestion();
		return Response.status(Status.OK).entity(list).build();
	}

	
	
	@GET
	@Path("/getCandidateWaitFortest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCandidateWaitFortest() {
		System.out.println("problem here 00");
		Set<Candidate> list = OTI.getCandidateWaitFortest();
		
		return Response.status(Status.OK).entity(list).build();
	}
	
	
	@GET
	@Path("/ListQuestionByModule")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestionByModule(@QueryParam(value = "module") String module) {
		Set<Question> list = OTI.ListQuestionByModule(module);
		return Response.status(Status.OK).entity(list).build();
	}

	@GET
	@Path("/ListModuleOfQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListModuleOfQuestion() {
		Set<String> list = OTI.ListModuleOfQuestion();
		return Response.status(Status.OK).entity(list).build();
	}
	
	@GET
	@Path("/getOnlineTestCandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOnlineTestCandidate(@QueryParam(value = "cid") int CandidateID) {
		
		OnlineTest ot = OTI.getOnlineTestCandidate(CandidateID);
		return Response.status(Status.OK).entity(ot).build();
		
		
		
	}
	

	@GET
	@Path("/ListResponceByQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListResponceByQuestion(@QueryParam(value = "qid") int QuestionID) {
		Set<Responce> list = OTI.ListResponceByQuestion(QuestionID);
		return Response.status(Status.OK).entity(list).build();
	}

	@PUT
	@Path("/updateResponce")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResponce(@QueryParam(value = "rid") int ResponceID, Responce responce) {
		OTI.updateResponce(ResponceID, responce);
		return Response.status(Status.OK).entity("responce has been updated").build();
	}

	@PUT
	@Path("/updateQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@QueryParam(value = "qid") int QuestionID, Question question) {
		OTI.updateQuestion(QuestionID, question);
		return Response.status(Status.OK).entity("question has been updated").build();

	}
	
	@PUT
	@Path("/UnAffectTestQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UnAffectTestQuestion( @QueryParam(value = "otid")int testID,@QueryParam(value = "qid") int QuestionID) {
		OTI.UnAffectTestQuestion(testID, QuestionID);
		return Response.status(Status.OK).entity("Question with id:["+QuestionID+"] Deleted from An Online Test with Id:["+testID+"]").build();

	}
	
	@PUT
	@Path("/affectAutoQuestionToTestByModule")
	public Response affectAutoQuestionToTestByModule(@QueryParam("module")String module, @QueryParam("NbQuestion")int NbQuestion, @QueryParam("otid")int testID)
	{
		OTI.affectAutoQuestionToTestByModule(module, NbQuestion, testID);
		return Response.status(Status.CREATED).entity("question add with succes").build();
	}
	
	@GET
	@Path("/EstimatedTimeForTest")
	public Response EstimatedTimeForTest(@QueryParam("otid")int TestID)
	{
		int x=OTI.EstimatedTimeForTest(TestID);
		return Response.status(Status.OK).entity("EstimatedTimeForTest : "+x).build();
	}
	
	@GET
	@Path("/ListQuestionByTest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestionByTest(@QueryParam("otid")int TestID)
	{
		Set<Question> list=OTI.ListQuestionByTest(TestID);
		return Response.status(Status.OK).entity(list).build();
	}
	
	
	
	
	@GET
	@Path("/GetOnlinetestResult")
	public Response GetOnlinetestResult(@QueryParam("otid")int TestID)
	{
		StateTestOnline x=OTI.GetOnlinetestResult(TestID);
		return Response.status(Status.OK).entity("OnlinetestResult : "+x).build();		
	}
	
	@PUT
	@Path("/setTestResult")
	public Response setTestResult(@QueryParam("otid")int TestID)
	{
		OTI.setTestResult(TestID);
		return Response.status(Status.OK).entity("result affected").build();
	}

	@PUT
	@Path("/setTestNoteByQuestion")
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response setTestNoteByQuestion(@QueryParam("otid")int TestID, @QueryParam("qid")int QuestionID, JsonArray jo)
	{
		Set<Integer> ResponcesID=new HashSet<Integer>();
					
		for(JsonValue j:jo)
		{			
			ResponcesID.add(Integer.valueOf(j.toString()));
		}
		double x=OTI.setTestNoteByQuestion(TestID, QuestionID, ResponcesID);
		
		return Response.status(Status.OK).entity("Note affected + "+x).build();
	}
	
	@DELETE
	@Path("/RemoveTestOnline")
	public Response RemoveTestOnline(@QueryParam("otid")int onlineTestID) {
		OTI.RemoveTestOnline(onlineTestID);
		return Response.status(Status.CREATED).build();
	}
	@DELETE
	@Path("/RemoveResponce")
	public Response RemoveResponce(@QueryParam("rid")int responceID) {
		OTI.RemoveResponce(responceID);
		return Response.status(Status.CREATED).entity("Responce has been deleted ").build();
	}
	@DELETE
	@Path("/RemoveQuestion")
	public Response RemoveQuestion(@QueryParam("qid")int questionID) {
		OTI.RemoveQuestion(questionID);
		return Response.status(Status.OK).entity("Question has been deleted ").build();
	}
}
