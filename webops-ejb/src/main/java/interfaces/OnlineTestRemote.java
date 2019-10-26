package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import entities.OnlineTest;
import entities.Question;
import entities.Responce;
@Remote
public interface OnlineTestRemote {
	
	public int AddOnlineTest(OnlineTest ot);
	public void addQuestion(Question q);
	public void addResponce(Responce r);
	
	public void affectTestToAnCandidate(int CandidateID,int TestID);
	public void AffectQuestionToAnOnlineTest(int TestID ,int QuestionID);
	public void AffectResponceToQuestion(int ResponceID,int QuestionID);
	
	
	public int AutoRefuseOnlineTest(int TestID);
	
	
	public Set<Question> ListQuestion();
	public Set<Question> ListQuestionByModule(String module);
	public Set<String> ListModuleOfQuestion();
	public Set<Responce> ListResponceByQuestion(int QuestionID);
	
	

}
