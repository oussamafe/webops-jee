package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
@Remote
public interface OnlineTestRemote {
	
	public int AddOnlineTest(OnlineTest ot);//tested OK
	public void addQuestion(Question q);//tested OK
	public void addResponce(Responce r);//tested OK
	
	public void affectTestToAnCandidate(int CandidateID,int TestID);//tested OK
	public void AffectQuestionToAnOnlineTest(int TestID ,int QuestionID);//tested OK
	public void AffectResponceToQuestion(int ResponceID,int QuestionID);//tested OK
	
	public void UnAffectTestQuestion(int testID,int questionID);//tested OK
	
	public  void updateQuestion(int QuestionID,Question question);//tested OK
	public  void updateResponce(int ResponceID,Responce responce);//tested OK
	
	public void affectAutoQuestionToTestByModule(String module,int NbQuestion, int testID);//tested OK
	
	public int EstimatedTimeForTest(int TestID);//tested OK
	public StateTestOnline GetOnlinetestResult(int TestID);//tested OK
	public void setTestResult(int TestID);//75% accepted :D  tested OK
	public void setTestNoteByQuestion(int TestID,int QuestionID,Set<Integer>ResponcesID);// tested OK
	
	
//	public int AutoRefuseOnlineTest(int TestID);//tested OK
	
	
	public Set<Question> ListQuestion();//tested OK
	public Set<Question> ListQuestionByModule(String module);//tested OK
	public Set<String> ListModuleOfQuestion();//tested OK
	public Set<Responce> ListResponceByQuestion(int QuestionID);//tested OK
	
	public void RemoveTestOnline(int onlineTestID);
	public void RemoveResponce(int responceID);
	public void RemoveQuestion(int questionID);
}
