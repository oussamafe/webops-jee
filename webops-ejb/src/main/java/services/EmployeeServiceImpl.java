package services;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mindrot.jbcrypt.BCrypt;

import entities.Employe;
import entities.User;
import interfaces.EmployeeServiceRemote;


@Stateless
@LocalBean
public class EmployeeServiceImpl implements EmployeeServiceRemote{

	
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	
	@Override
	public int addEmployee(Employe emp) {
		
		List<User> lista = showAllUsers();
		for(User user : lista)
		{
			if(user.getEmail().equals(emp.getEmail()))
				return 0;
		}
		
		String pass = BCrypt.hashpw(emp.getPassword(), BCrypt.gensalt());
		emp.setPassword(pass);
		em.persist(emp);
		return emp.getId();
	}

	@Override
	public boolean editEmployee(Employe emp, int idEmp) {
		Employe employe = em.find(Employe.class, idEmp);
		if(employe != null)
		{
			emp.setId(employe.getId());
			em.merge(emp);
		}
		return false;
			
		
	}

	@Override
	public Employe showEmployeeDetails(int idEmp) {
		Employe emp = em.find(Employe.class, idEmp);
		if(emp != null)
			return emp;
		return null;
	}

	@Override
	public void addEmployeeToCompany(int idEmp, int idCompany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEmployeeFromCompany(int idEmp, int idComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showAllUsers() {
		List<User> users = 	em.createQuery("from User", User.class).getResultList();
		if(users !=null)
		{
			return users;
		}
		return null;
	}

}
