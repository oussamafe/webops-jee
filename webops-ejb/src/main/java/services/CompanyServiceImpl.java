package services;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Company;
import interfaces.CompanyInterfaceRemote;

@Stateless
@LocalBean
public class CompanyServiceImpl implements CompanyInterfaceRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int registerCompany(Company company) {

		try {
			em.persist(company);
		} catch (PersistenceException e) {
			return -1;
		}
		return company.getId();
	}

	@Override
	public int removeComany(int idC) {
		
		Company company = em.find(Company.class, idC);
		em.remove(company);
		return 1;
	}

}
