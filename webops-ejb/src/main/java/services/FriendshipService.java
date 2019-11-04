package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.ApplicationId;
import entities.Candidate;
import entities.Course;
import entities.Friend;
import entities.FriendID;
import entities.JobOffer;
import interfaces.FriendInterfaceRemote;
@Stateless
@LocalBean
public class FriendshipService implements FriendInterfaceRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	@Override
	public void sendFriendRequest(Friend F,int idSender, int Reciever,int idSender1) {
		Date dateRequest = new Date();
		//System.out.println(dateRequest);
		FriendID PK = new FriendID();
		PK.setCandidateId(idSender);
		PK.setFriendID(Reciever);
		F.setSender(idSender1);
		F.setId(PK);
		F.setDateRequest(dateRequest);
		em.persist(F);
	}


	@Override
	public void RemoveFriend(int idCandidate ,int idfriend ) {
	int query=
	em.createQuery(
	"Delete from Friend F"
	+ " where (F.Friend="+idfriend+" and F.Candidate="+idCandidate+"and F.state=true)OR (F.Friend="+ idCandidate+"and F.Candidate="+idfriend+"and F.state=true)")
				  .executeUpdate();
	

	}
	
	@Override
	public List<Candidate> getAllMyFriends(int Candidate) {
	TypedQuery<Candidate> query= em.createQuery(
			"select C  from Candidate C Join C.Friends CF where CF.state=true and CF.Candidate="+Candidate, 
			Candidate.class);
	List<Candidate> list1=query.getResultList();
	System.out.println(list1);
	TypedQuery<Candidate> query1= em.createQuery(
			"select C  from Candidate C Join C.Friends CF where CF.state=true and CF.Friend="+Candidate, 
			Candidate.class);
	List<Candidate> list2=query1.getResultList();
	List<Candidate> list3= new ArrayList<Candidate>();
	for (int i = 0; i < list1.size(); i++) {
		for (int j = 0; j < list1.size(); j++) {
		if(list1.get(i).getId()!=Candidate) {
			list3.add(list1.get(i));
			return list1;
		}else if (list2.get(i).getId()!=Candidate)
		
			list3.add(list2.get(j));
		
		}}
	return list3;
	
	}

	@Override
	public List<Candidate> getAllMyFriendRequest(int Candidate) {
		
		TypedQuery<Candidate> query= em.createQuery(
				"select C  from Candidate C Join C.Friends CF where CF.state=false and CF.dateFriendship is null and CF.Sender<>"+Candidate +" and CF.Candidate="+Candidate, 
				Candidate.class);
	List<Candidate> l=query.getResultList();
		return l;
	}


	@Override
	public void AcceptFriendRequest(int idSender, int idReciever) {
		Date dateAjout = new Date();
		
		Query query2 = 
		em.createQuery("update Friend C set C.state=:state , C.dateFriendship=:dateAjout where C.Friend="+idReciever+ "and C.Candidate="+idSender);
		query2.setParameter("state", true);
		query2.setParameter("dateAjout", dateAjout);
		
		query2.executeUpdate();
		
	}


	@Override
	public void RejectFriendRequest(int idSender, int idReciever) {
		int query=
				em.createQuery(
				"Delete from Friend F"
				+ " where (F.Friend="+idReciever+" and F.Candidate="+idSender+"and F.state=false and F.dateFriendship is Null)")
							  .executeUpdate();
				

		
	}

}
