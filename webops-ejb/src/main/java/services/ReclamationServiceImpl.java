package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 
import entities.Reclamation;
import entities.User;
import interfaces.ReclamtionImplimentation;

@Stateless
@LocalBean
public class ReclamationServiceImpl implements ReclamtionImplimentation {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	@Override
	public int CreateClaim(Reclamation R, int user_id) {
		em.persist(R);
		em.flush();
		Reclamation claims = em.find(Reclamation.class, R.getId());  
		User user = new User();
		user.setId(user_id);
		claims.setUser( user); 
		em.merge(claims);
		return R.getId();
	}

	@Override
	public List<Reclamation> ReadClaim() {
		List<Reclamation> list = em.createQuery("from Reclamation",Reclamation.class).getResultList();
		return list;
	}
	@Override
	public List<Reclamation> ShowClaimByEtat(String etat) {
		List<Reclamation> list = em.createQuery("from Reclamation where etat="+etat,Reclamation.class).getResultList();
		return list;
	}
	@Override
	public void UpdateClaim(Reclamation R) {
		// TODO Auto-generated method stub
		Reclamation claims = em.find(Reclamation.class, R.getId()); 
		claims.setEtat(R.getEtat()); 
		claims.setLibelle(R.getLibelle()); 
		claims.setMessage(R.getMessage());
		claims.setSujet(R.getSujet()); 
		em.merge(claims);	
	}

	@Override
	public void DeleteClaim(int id) {
		Reclamation claim = em.find(Reclamation.class, id);
		em.remove(claim);
		
	}

	@Override
	public List<Reclamation> SuivieClaim(int Userid) {
		List<Reclamation> list = em.createQuery("from Reclamation R where R.user="+Userid,Reclamation.class).getResultList();
		return list;
	}

	@Override
	public void BestClaimTraiter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AffectClaimToTrait() {
		// TODO Auto-generated method stub
		
	}

}
