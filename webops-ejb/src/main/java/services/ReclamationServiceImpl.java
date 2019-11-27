package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Reclamation;
import interfaces.ReclamtionImplimentation;

@Stateless
@LocalBean
public class ReclamationServiceImpl implements ReclamtionImplimentation {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	@Override
	public int CreateClaim(Reclamation R) {
		em.persist(R);
		em.flush();
		return R.getId();
	}

	@Override
	public List<Reclamation> ReadClaim() {
		List<Reclamation> list = em.createQuery("from Reclamation",Reclamation.class).getResultList();
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

}
