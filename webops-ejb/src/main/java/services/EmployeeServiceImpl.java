package services;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.mindrot.jbcrypt.BCrypt;

import entities.Company;
import entities.Employe;
import entities.Role;
import entities.User;
import interfaces.EmployeeServiceRemote;

@Stateless
@LocalBean
public class EmployeeServiceImpl implements EmployeeServiceRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int addAdmin(Employe emp, int idCompany) {

		Company company = em.find(Company.class, idCompany);
		if (company != null) {

			for (Employe employees : company.getEmployes()) {
				if (employees.getRole().equals(Role.Administrator))
					return 0;
			}
			if (!company.getEmployes().contains(emp)) {
				emp.setCompany(company);
				String hashpass = BCrypt.hashpw(emp.getPassword(), BCrypt.gensalt());
				emp.setPassword(hashpass);
				try {
					em.persist(emp);
				} catch (PersistenceException e) {
					return -1;
				}
				return emp.getId();
			}
		}
		return 0;
	}

	@Override
	public int addEmployee(Employe emp, int idCompany) {

		Company company = em.find(Company.class, idCompany);
		if (company != null) {
			if (company.getEmployes().contains(emp))
				return 0;
			for (Employe employe : company.getEmployes())
				if (employe.getRole().equals(emp.getRole()))
					return 0;
			emp.setCompany(company);
			String hashpass = BCrypt.hashpw(emp.getPassword(), BCrypt.gensalt());
			emp.setPassword(hashpass);
			try {
				em.persist(emp);
				return emp.getId();
			} catch (PersistenceException e) {
				return -1;
			}
		}
		return 0;
	}

	@Override
	public int disableEmployee(int idE) {
		Employe employe = em.find(Employe.class, idE);
		employe.setActive(false);
		return 1;
	}

	@Override
	public int confirmAccountEmployee(int idE, String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editEmployeeAccount(Employe emp, int idE) {
		Employe employe = em.find(Employe.class, idE);
		if(employe != null)
		{
			emp.setId(employe.getId());
			em.merge(emp);
			return emp.getId();
		}
		return 0;
	}

	@Override
	public Employe showEmployeeDetails(int idE) {
		Employe employe = em.find(Employe.class, idE);
		if (employe != null)
			return employe;
		return null;
	}

}
