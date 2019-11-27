package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Application;
import entities.Candidate;
import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
import interfaces.OnlineTestRemote;
import utilities.MailClass;

@Stateless
@LocalBean
public class OnlineTestImplementation implements OnlineTestRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	
	@Inject
	AvailabilityImplementation ai;
	@Inject
	InterviewManagementImplimentation ami;
	private Integer id;
	
	
	
	@Override
	public int AddOnlineTest() {
		OnlineTest ot=new OnlineTest();
		ot.setState(StateTestOnline.WaitForIt);
		em.persist(ot);
		return ot.getId();
	}

	@Override
	public void addQuestion(Question q) {
		em.persist(q);

	}

	@Override
	public int addResponce(Responce r) {
		em.persist(r);
		return r.getId();

	}

	@Override
	public void affectTestToAnCandidate(int CandidateID, int TestID,Date datetest) {
		Candidate can = em.find(Candidate.class, CandidateID);
		OnlineTest test = em.find(OnlineTest.class, TestID);
		test.setDate(datetest);
		can.setOnlineTest(test);
		test.setCandidatTest(can);
		String desc="congratulations your application has been accepted and you have assigned to an online test \n "
				+ "you have three days to do it from this date  "+datetest+" \n"
				+ "check this link for the online test \n"
				+ "http://localhost:9080/webops-web/rest/OnlineTestResources/getOnlineTestCandidate?cid="+CandidateID;
		System.out.println("email= "+can.getEmail());
		new MailClass(can.getEmail(), "CONGRATULATIONS_APPLICATION_ACCEPTED", desc);
	}

	@Override
	public void AffectQuestionToAnOnlineTest(int TestID, int QuestionID) {
		OnlineTest test = em.find(OnlineTest.class, TestID);
		Question Q = em.find(Question.class, QuestionID);
		Q.getOnlineTests().add(test);
		test.getQuestions().add(Q);

	}

	@Override
	public void AffectResponceToQuestion(int ResponceID, int QuestionID) {
		Responce r = em.find(Responce.class, ResponceID);
		Question q = em.find(Question.class, QuestionID);
		q.getReponces().add(r);
		r.setQuestionReponce(q);

	}

	

	@Override
	public Set<Question> ListQuestion() {
		TypedQuery<Question> query = em.createQuery("SELECT a FROM Question a ", Question.class);
		Set<Question> results = new HashSet<Question>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Question> ListQuestionByModule(String module) {
		TypedQuery<Question> query = em.createQuery("SELECT a FROM Question a where a.Module=:m", Question.class);
		query.setParameter("m", module);
		Set<Question> results = new HashSet<Question>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<String> ListModuleOfQuestion() {
		TypedQuery<String> query = em.createQuery("SELECT a.Module FROM Question a ", String.class);
		Set<String> results = new HashSet<String>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Set<Responce> ListResponceByQuestion(int QuestionID) {
		Question q = em.find(Question.class, QuestionID);
		TypedQuery<Responce> query = em.createQuery("SELECT a FROM Responce a where a.questionReponce=:m",
				Responce.class);
		query.setParameter("m", q);
		Set<Responce> results = new HashSet<Responce>();
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public void updateQuestion(int QuestionID, Question question) {	
		Question q = em.find(Question.class, QuestionID);
		q.setEstimated_Time(question.getEstimated_Time());
		q.setModule(question.getModule());
		q.setQuestion(question.getQuestion());
	}	

	@Override
	public void updateResponce(int ResponceID, Responce responce) {
		Responce q = em.find(Responce.class, ResponceID);
		q.setIsValid(responce.IsValid());
		q.setReponce(responce.getReponce());
	}

	@Override
	public void affectAutoQuestionToTestByModule(String module, int NbQuestion, int testID) {
		List<Question> questions = new ArrayList<Question>();
		questions.addAll(ListQuestionByModule(module));

		if (questions.size() <= NbQuestion) {
			for (Question q : questions) {
				AffectQuestionToAnOnlineTest(testID, q.getId());
			}
		} else {
			Random rand = new Random();
			Set<Integer> num = new HashSet<Integer>();
			while (num.size() != NbQuestion) {
				num.add(rand.nextInt(questions.size()));
			}
			for (Integer i : num) {
				AffectQuestionToAnOnlineTest(testID, questions.get(i).getId());
			}
		}

	}

	@Override
	public int EstimatedTimeForTest(int TestID) {
		OnlineTest ot = em.find(OnlineTest.class, TestID);
		int s = 0;
		for (Question q : ot.getQuestions()) {
			s += q.getEstimated_Time();
		}
		return s;
	}

	@Override
	public StateTestOnline GetOnlinetestResult(int TestID) {
		OnlineTest ot = em.find(OnlineTest.class, TestID);
		return ot.getState();
	}

	@Override
	public void setTestResult(int TestID) {// 75% accepted
		OnlineTest ot = em.find(OnlineTest.class, TestID);
		if (ot.getNote() < 15)
			ot.setState(StateTestOnline.InValid);
		else
		{
				ot.setState(StateTestOnline.Valid);
				ai.InitialiseCandidateAvailability(ot.getCandidatTest().getId());
				ami.AddInterview(ot.getCandidatTest().getId());
				
			}
	}

	/**
	 * ResponcesID : responces id that the candidate choices in this Question
	 */
	@Override
	public double setTestNoteByQuestion(int TestID, int QuestionID, Set<Integer> ResponcesID) {
		OnlineTest ot = em.find(OnlineTest.class, TestID);
		Question q = em.find(Question.class, QuestionID);
		double nbTrueOwned = 0;
		int nbTrueResp = 0;
		for (Integer id : ResponcesID) {
			Responce r = em.find(Responce.class, id);
			nbTrueOwned = (r.IsValid() ? nbTrueOwned + 1 : nbTrueOwned - 1);
		}
		for (Responce r : q.getReponces()) {
			if (r.IsValid()) {
				nbTrueResp++;
			}
		}
		double noteTotQuestion = 20 / ot.getQuestions().size();
		double noteTotResponce = noteTotQuestion / nbTrueResp;
		double note = nbTrueOwned > 0 ? ot.getNote() + (nbTrueOwned * noteTotResponce) : ot.getNote();

		ot.setNote(note);
		return note;
	}

	@Override
	public void UnAffectTestQuestion(int testID, int questionID) {
		OnlineTest ot = em.find(OnlineTest.class, testID);
		Question q = em.find(Question.class, questionID);
		
		ot.getQuestions().remove(q);
		q.getOnlineTests().remove(ot);

	}

	@Override
	public void RemoveTestOnline(int onlineTestID) {
		OnlineTest ot = em.find(OnlineTest.class, onlineTestID);
		em.remove(ot);
		
	}

	@Override
	public void RemoveResponce(int responceID) {
		Responce r = em.find(Responce.class, responceID);
		Question q=em.find(Question.class,r.getQuestionReponce().getId());
		q.getReponces().remove(r);
		em.remove(r);
	}

	@Override
	public void RemoveQuestion(int questionID) {
		Question q = em.find(Question.class, questionID);
		em.remove(q);
		
	}

	@Override
	public OnlineTest getOnlineTestCandidate(int CandidateID) {
		Candidate can = em.find(Candidate.class, CandidateID);
		
		try {
			OnlineTest ot = em.find(OnlineTest.class, can.getOnlineTest().getId());
			return ot;
		} catch (NullPointerException e) {
			return null;
		}
		
	}

	@Override
	public Set<Candidate> getCandidateWaitFortest() {
		
		TypedQuery<Application> query = em.createQuery("SELECT a FROM Application a where a.result = 1  ", Application.class);
		Set<Application> applications = new HashSet<Application>();
		applications.addAll(query.getResultList());
		
		Set<Candidate> candidates = new HashSet<Candidate>();
		for(Application app: applications)
		{
			candidates.add(app.getCandidate());
		}
		
		
		TypedQuery<OnlineTest> query2 = em.createQuery("SELECT a FROM OnlineTest a  ", OnlineTest.class);
		Set<OnlineTest> onlines = new HashSet<OnlineTest>();
		onlines.addAll(query2.getResultList());
		
		
		for(OnlineTest on: onlines)
		{
			candidates.remove(on.getCandidatTest());
		}
		
		
		
		
		
		return candidates;
	}

	@Override
	public Set<Question> ListQuestionByTest(int testID) {
		OnlineTest ot=em.find(OnlineTest.class, testID);
		TypedQuery<Question> query = em.createQuery("SELECT a FROM Question a ", Question.class);		
		Set<Question> qus = new HashSet<Question>();
		qus.addAll(query.getResultList());
		
		Set<Question> results = new HashSet<Question>();
		for(Question q:qus)
		{
			if(q.getOnlineTests().contains(ot))
			{
				results.add(q);
			}
		}
		
		return results;
	}

	
}
