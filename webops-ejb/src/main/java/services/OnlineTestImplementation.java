package services;

import java.util.HashSet;
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
public class OnlineTestImplementation implements OnlineTestRemote{

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int AddOnlineTest(OnlineTest ot) {
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
		Candidate can=em.find(Candidate.class, CandidateID);
		OnlineTest test=em.find(OnlineTest.class, TestID);
		can.setOnlineTest(test);
		test.setCandidatTest(can);
	}

	@Override
	public void AffectQuestionToAnOnlineTest(int TestID, int QuestionID) {
		OnlineTest test=em.find(OnlineTest.class, TestID);
		Question Q=em.find(Question.class, QuestionID);
		Q.getOnlineTests().add(test);
		test.getQuestions().add(Q);
		
	}

	@Override
	public void AffectResponceToQuestion(int ResponceID, int QuestionID) {
		Responce r=em.find(Responce.class, ResponceID);
		Question q=em.find(Question.class, QuestionID);
		q.getReponces().add(r);
		r.setQuestionReponce(q);
		
	}

	@Override
	public int AutoRefuseOnlineTest(int TestID) {		
		Query query = em.createQuery("UPDATE onlinetest o SET o.state=:s WHERE o.date +5 < NOW()");
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
		query.setParameter("m",module);
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
		Question q=em.find(Question.class, QuestionID);
		TypedQuery<Responce> query = em.createQuery("SELECT a FROM Responce a where a.questionReponce=:m", Responce.class);
		query.setParameter("m",q);
		Set<Responce> results = new HashSet<Responce>();
		results.addAll(query.getResultList());
		return results;
	}
}
