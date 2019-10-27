package resources;

import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Path("/affectCandidateTest/{CandidateID}/{TestID}")
	public Response affectTestToAnCandidate(@PathParam(value = "CandidateID") int CandidateID,
			@PathParam(value = "TestID") int TestID) {
		OTI.affectTestToAnCandidate(CandidateID, TestID);

		return Response.status(Status.OK).build();

	}

	@PUT
	@Path("/affectQuestionTest/{TestID}/{QuestionID}")
	public Response AffectQuestionToAnOnlineTest(@PathParam(value = "TestID") int TestID,
			@PathParam(value = "QuestionID") int QuestionID) {
		OTI.AffectQuestionToAnOnlineTest(TestID, QuestionID);

		return Response.status(Status.OK).build();

	}

	@PUT
	@Path("/affectQuestionResponce/{ResponceID}/{QuestionID}")
	public Response AffectResponceToQuestion(@PathParam(value = "ResponceID") int ResponceID,
			@PathParam(value = "QuestionID") int QuestionID) {
		OTI.AffectResponceToQuestion(ResponceID, QuestionID);

		return Response.status(Status.OK).build();

	}

	@PUT
	@Path("/AutoRefuseOnlineTest/{TestID}")
	public Response AutoRefuseOnlineTest(@PathParam(value = "TestID") int TestID) {
		int x = OTI.AutoRefuseOnlineTest(TestID);

		return Response.status(Status.OK).entity(x).build();

	}

	@GET
	@Path("/ListQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestion() {
		Set<Question> list = OTI.ListQuestion();
		return Response.status(Status.OK).entity(list).build();
	}

	@GET
	@Path("/ListQuestionByModule/{module}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListQuestionByModule(@PathParam(value = "module") String module) {
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
	@Path("/ListResponceByQuestion/{QuestionID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListResponceByQuestion(@PathParam(value = "QuestionID") int QuestionID) {
		Set<Responce> list = OTI.ListResponceByQuestion(QuestionID);
		return Response.status(Status.OK).entity(list).build();
	}

	@PUT
	@Path("/updateResponce/{ResponceID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResponce(@PathParam(value = "ResponceID") int ResponceID, Responce responce) {
		OTI.updateResponce(ResponceID, responce);
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("/updateQuestion/{QuestionID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@PathParam(value = "QuestionID") int QuestionID, Question question) {
		OTI.updateQuestion(QuestionID, question);
		return Response.status(Status.OK).build();

	}
	
	@PUT
	@Path("/UnAffectTestQuestion/{testID}/{QuestionID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UnAffectTestQuestion( @PathParam(value = "testID")int testID,@PathParam(value = "QuestionID") int QuestionID) {
		OTI.UnAffectTestQuestion(testID, QuestionID);
		return Response.status(Status.OK).build();

	}
	
	@PUT
	@Path("/affectAutoQuestionToTestByModule/{module}/{NbQuestion}/{testID}")
	public Response affectAutoQuestionToTestByModule(@PathParam(value = "module")String module, @PathParam(value = "NbQuestion")int NbQuestion, @PathParam(value = "testID")int testID)
	{
		OTI.affectAutoQuestionToTestByModule(module, NbQuestion, testID);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/EstimatedTimeForTest/{TestID}")
	public Response EstimatedTimeForTest(@PathParam(value = "TestID")int TestID)
	{
		int x=OTI.EstimatedTimeForTest(TestID);
		return Response.status(Status.OK).entity("EstimatedTimeForTest : "+x).build();
	}
	
	@GET
	@Path("/GetOnlinetestResult/{TestID}")
	public Response GetOnlinetestResult(@PathParam(value = "TestID")int TestID)
	{
		StateTestOnline x=OTI.GetOnlinetestResult(TestID);
		return Response.status(Status.OK).entity("OnlinetestResult : "+x).build();		
	}
	
	@PUT
	@Path("/setTestResult/{TestID}")
	public Response setTestResult(@PathParam(value = "TestID")int TestID)
	{
		OTI.setTestResult(TestID);
		return Response.status(Status.OK).build();
	}
	//// need to test it XD 
	@PUT
	@Path("/setTestNoteByQuestion/{TestID}/{QuestionID}")
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response setTestNoteByQuestion(@PathParam(value = "TestID")int TestID, @PathParam(value = "QuestionID")int QuestionID, JsonArray jo)
	{
		Set<Integer> ResponcesID=new HashSet<Integer>();
					
		for(JsonValue j:jo)
		{			
			ResponcesID.add(Integer.valueOf(j.toString()));
		}
		OTI.setTestNoteByQuestion(TestID, QuestionID, ResponcesID);
		
		return Response.status(Status.OK).build();
	}
}