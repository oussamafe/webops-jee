package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;
import interfaces.UserInterfaceRemote;

@Stateless
@LocalBean
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


	@Override
	public void setRefreshToken(int idU , String refreshToken) {
		User u = em.find(User.class, idU);
		if(u != null)
		{
			u.setRefresh_token(refreshToken);
		}
		
	}


	@Override
	public boolean checkRefreshToken(int idU, String token) {
		User u = em.find(User.class, idU);
		if(u != null)
		{
			return (u.getRefresh_token().equals(token));
		}
		
		return false;
	}

}
