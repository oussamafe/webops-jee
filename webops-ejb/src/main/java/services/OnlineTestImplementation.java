package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Candidate;
import entities.OnlineTest;
import entities.Question;
import entities.Responce;
import entities.StateTestOnline;
import interfaces.OnlineTestRemote;

@Stateless
@LocalBean
public class OnlineTestImplementation implements OnlineTestRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	private Integer id;

	@Override
	public int AddOnlineTest(OnlineTest ot) {
		ot.setState(StateTestOnline.WaitForIt);
		em.persist(ot);
		return ot.getId();
	}

	@Override
	public void addQuestion(Question q) {
		em.persist(q);

	}

	@Override
	public void addResponce(Responce r) {
		em.persist(r);

	}

	@Override
	public void affectTestToAnCandidate(int CandidateID, int TestID) {
		Candidate can = em.find(Candidate.class, CandidateID);
		OnlineTest test = em.find(OnlineTest.class, TestID);
		can.setOnlineTest(test);
		test.setCandidatTest(can);
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
	public int AutoRefuseOnlineTest(int TestID) {
		Query query = em.createQuery("UPDATE OnlineTest o SET o.state=:s WHERE o.date +5 < NOW()");
		query.setParameter("s", StateTestOnline.InValid);
		return query.executeUpdate();
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
			ot.setState(StateTestOnline.Valid);
	}

	/**
	 * ResponcesID : responces id that the candidate choices in this Question
	 */
	@Override
	public void setTestNoteByQuestion(int TestID, int QuestionID, Set<Integer> ResponcesID) {
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

	}

	@Override
	public void UnAffectTestQuestion(int testID, int questionID) {
		OnlineTest ot = em.find(OnlineTest.class, testID);
		Question q = em.find(Question.class, questionID);
		
		ot.getQuestions().remove(q);
		q.getOnlineTests().remove(ot);

	}
}