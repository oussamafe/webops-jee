package services;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.Post;
import interfaces.CommentServiceLocal;
import interfaces.CommentServiceRemote;

@Stateless
@LocalBean
public class CommentService implements CommentServiceLocal, CommentServiceRemote{
	
	@PersistenceContext
    EntityManager em; 
	
	public CommentService() {	
	}
	
	@Override
	public void addComment(Comment comment) {
		em.persist(comment);
	}
	
	@Override
	public void deleteComment(int id) {
		em.remove(em.find(Post.class, id ));	
	}
	
	@Override
	public Comment findComment(int id) {	
		return em.find(Comment.class, id );
	}
	
	@Override
	public void updateComment(Comment comment) {
		em.merge(comment);
	}

}
