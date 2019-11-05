package services;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	 public int compterUser (int id,Post p) {
	    	Query query= em.createQuery("SELECT COUNT(p.User) FROM Post p where p.User="+id);
	    	return (int) query.getSingleResult();
	    }
	    
	    public List<Post> listerPosts(){
	    	TypedQuery<Post> query = em.createQuery("select p from Post p",Post.class);
	    
	    	return query.getResultList();
	    	
	    	
	    }
	    public List<Post> getAllPosts() {
			TypedQuery<Post> query;
			
			query=em.createQuery("SELECT e FROM Post e",Post.class);
			
			
			List<Post>publications=null;
			
			
			System.out.println( "1"+publications);
			
			
			try {
				
				System.out.println("2" +publications);
			
				publications=query.getResultList();
			
				System.out.println("3" +publications);
				
				
			}
			catch (NoResultException e) {
				
				System.out.println("erreur");
			}
			
			
			return publications;
		}
	

}
