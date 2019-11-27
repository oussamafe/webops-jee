package services;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Post;
import interfaces.PostServiceLocal;
import interfaces.PostServiceRemote;


@Stateless
@LocalBean
public class PostService implements PostServiceLocal, PostServiceRemote {
	
	@PersistenceContext
    EntityManager em; 
	
	public PostService() {
		
	}
	
	@Override
	public void addPost(Post post) {
		em.persist(post);
	}
	
	@Override
	public void deletePost(int id_post) {
		em.remove(em.find(Post.class, id_post ));	
	}
	
	@Override
	public Post findPost(int id_post) {	
		return em.find(Post.class, id_post );
	}
	
	@Override
	public void updatePost(Post post) {
		em.merge(post);
	}
	

}
