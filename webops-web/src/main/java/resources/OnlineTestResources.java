package resources;

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


import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
import services.OnlineTestImplementation;

@RequestScoped
@Path("OnlineTestResources")
public class OnlineTestResources {
	@Inject
	OnlineTestImplementation OTI;

	@POST
	@Path("/AddOnlineTest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddOnlineTest(OnlineTest ot) {
		int x = OTI.AddOnlineTest(ot);
		return Response.status(Status.CREATED).entity(x).build();
	}

	@POST
	@Path("/addTestQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestion(Question q) {
		OTI.addQuestion(q);
		return Response.status(Status.CREATED).build();
	}

	@POST
	@Path("/addQuestionResponce")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResponce(Responce r) {
		OTI.addResponce(r);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("/affectCandidateTest")
	public Response affectTestToAnCandidate(@QueryParam(value = "cid") int CandidateID,
			@QueryParam(value = "otid") int TestID) {
		OTI.affectTestToAnCandidate(CandidateID, TestID);

		return Response.status(Status.OK).entity("Candidate with id:["+CandidateID+"] Affected To An Online Test with Id:["+TestID+"]").build();

	}

	@PUT
	@Path("/affectQuestionTest")
	public Response AffectQuestionToAnOnlineTest(@QueryParam(value = "otid") int TestID,
			@QueryParam(value = "qid") int QuestionID) {
		OTI.AffectQuestionToAnOnlineTest(TestID, QuestionID);

		return Response.status(Status.OK).entity("Question with id:["+QuestionID+"] Affected To An Online Test with Id:["+TestID+"]").build();

	}

	@PUT
	@Path("/affectQuestionResponce")
	public Response AffectResponceToQuestion(@QueryParam(value = "rid") int ResponceID,
			@QueryParam(value = "qid") int QuestionID) {
		OTI.AffectResponceToQuestion(ResponceID, QuestionID);

		return Response.status(Status.OK).entity("Responce with id:["+ResponceID+"] Affected To a Question  with Id:["+QuestionID+"]").build();

	}

	/*@PUT
	@Path("/AutoRefuseOnlineTest")
	public Response AutoRefuseOnlineTest(@QueryParam(value = "otid") int TestID) {
		int x = OTI.AutoRefuseOnlineTest(TestID);

		return Response.status(Status.OK).entity(x).build();

	}
*/
	@GET
	@Path("/ListQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestion() {
		Set<Question> list = OTI.ListQuestion();
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
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("/updateQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@QueryParam(value = "qid") int QuestionID, Question question) {
		OTI.updateQuestion(QuestionID, question);
		return Response.status(Status.OK).build();

	}
	
	@PUT
	@Path("/UnAffectTestQuestion/{testID}/{QuestionID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UnAffectTestQuestion( @QueryParam(value = "otid")int testID,@QueryParam(value = "qid") int QuestionID) {
		OTI.UnAffectTestQuestion(testID, QuestionID);
		return Response.status(Status.OK).build();

	}
	
	@PUT
	@Path("/affectAutoQuestionToTestByModule")
	public Response affectAutoQuestionToTestByModule(@QueryParam("module")String module, @QueryParam("NbQuestion")int NbQuestion, @QueryParam("otid")int testID)
	{
		OTI.affectAutoQuestionToTestByModule(module, NbQuestion, testID);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/EstimatedTimeForTest")
	public Response EstimatedTimeForTest(@QueryParam("otid")int TestID)
	{
		int x=OTI.EstimatedTimeForTest(TestID);
		return Response.status(Status.OK).entity("EstimatedTimeForTest : "+x).build();
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
		return Response.status(Status.OK).build();
	}
	//// need to test it XD 
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
		OTI.setTestNoteByQuestion(TestID, QuestionID, ResponcesID);
		
		return Response.status(Status.OK).build();
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
		return Response.status(Status.CREATED).build();
	}
	@DELETE
	@Path("/RemoveQuestion")
	public Response RemoveQuestion(@QueryParam("qid")int questionID) {
		OTI.RemoveQuestion(questionID);
		return Response.status(Status.CREATED).build();
	}
}
