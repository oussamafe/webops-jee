package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;
import interfaces.UserInterfaceRemote;

public class LoginServiceImpl implements UserInterfaceRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	
	@Override
	public User login(String username, String password) {
		
		TypedQuery<User> query = em.createQuery("select e from User e where e.email=:email", User.class);
		query.setParameter("email", username);
		User u = query.getSingleResult();
		if(BCrypt.checkpw(password, u.getPassword()))
		{
			return u;
		}
		return null;
	}

}
