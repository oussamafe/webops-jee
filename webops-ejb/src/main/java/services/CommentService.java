package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.CommentServiceLocal;
import interfaces.CommentServiceRemote;

@Stateless
@LocalBean
public class CommentService implements CommentServiceLocal, CommentServiceRemote {
	
	@PersistenceContext
    EntityManager em; 
	
	public CommentService() {	
	}

}
