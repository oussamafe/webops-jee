package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Packs;
import interfaces.PackImplimentation;

@Stateless
@LocalBean
public class PackServiceImpl implements PackImplimentation {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int CreatePack(Packs P) {
		// TODO Auto-generated method stub
		em.persist(P);
		em.flush();
		return P.getId();
	}

	@Override
	public List<Packs> ReadPack() {
		List<Packs> list = em.createQuery("from Packs",Packs.class).getResultList();
		return list;
	}

	@Override
	public void UpdatePack(Packs pack) {
		// TODO Auto-generated method stub
		Packs packs = em.find(Packs.class, pack.getId()); 
		packs.setDescription(pack.getDescription()); 
		packs.setLibelle(pack.getLibelle()); 
		packs.setPrix(pack.getPrix()); 
		em.merge(packs);
	}

	@Override
	public void DeletePack(int id) {
		// TODO Auto-generated method stub
		Packs packs = em.find(Packs.class, id);

		  em.remove(packs);
	}

}
