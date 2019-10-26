package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
@Remote
public interface OnlineTestRemote {
	
	public int AddOnlineTest(OnlineTest ot);
	public void addQuestion(Question q);
	public void addResponce(Responce r);
	
	public void affectTestToAnCandidate(int CandidateID,int TestID);
	public void AffectQuestionToAnOnlineTest(int TestID ,int QuestionID);
	public void AffectResponceToQuestion(int ResponceID,int QuestionID);
	
	
	public  void updateQuestion(int QuestionID,Question question);
	public  void updateResponce(int ResponceID,Responce responce);
	
	public void affectAutoQuestionToTestByModule(String module,int NbQuestion, int testID);
	
	public int EstimatedTimeForTest(int TestID);
	public StateTestOnline GetOnlinetestResult(int TestID);
	public void setTestResult(int TestID);//70% accepted :D
	public void setTestNoteByQuestion(int TestID,int QuestionID,Set<Integer>ResponcesID);
	
	
	public int AutoRefuseOnlineTest(int TestID);
	
	
	public Set<Question> ListQuestion();
	public Set<Question> ListQuestionByModule(String module);
	public Set<String> ListModuleOfQuestion();
	public Set<Responce> ListResponceByQuestion(int QuestionID);
	

}
