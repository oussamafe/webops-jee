package services;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import entities.Company;
import entities.Employe;
import entities.User;
import interfaces.EmployeeServiceRemote;


@Stateless
@LocalBean
public class EmployeeServiceImpl implements EmployeeServiceRemote{

	
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	
	@Override
<<<<<<< webops-ejb/src/main/java/services/EmployeeServiceImpl.java
	public int registerAdmin(Employe employe) {

		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		employe.setConfirmationToken(generatedString);

		String hashpass = BCrypt.hashpw(employe.getPassword(), BCrypt.gensalt());
		employe.setPassword(hashpass);
		try {

			em.persist(employe);
			String Subject = "Verify your Account ! ";
			String message = "Hi, \r\n" + "Please verify your account by clicking the link below: \r\n"
					+ "http://localhost:9080/webops-web/rest/employee/" + employe.getId() + "/" + generatedString + "";
			mailService.Send(null, null, employe.getEmail(), Subject, message);
			return employe.getId();
		} catch (PersistenceException | MessagingException | UnsupportedEncodingException e) {
			return 0;
		}

	}

	@Override
	public boolean activateAccount(int idE, String token) {

		Employe employe = em.find(Employe.class, idE);
		if (employe != null) {
			if (employe.getConfirmationToken().equals(token)) {
				employe.setActive(true);
				employe.setConfirmationToken(null);
				return true;
			} else {
				String Subject = "Verify your Account ! ";
				String message = "Hi, \r\n" + "Please verify your account by clicking the link below: \r\n"
						+ "http://localhost:9080/webops-web/rest/employee/" + employe.getId() + "/"
						+ employe.getConfirmationToken() + "";
				try {
					mailService.Send(null, null, employe.getEmail(), Subject, message);
				} catch (UnsupportedEncodingException | MessagingException e) {
				}
			}
		}
		return false;
	}

	@Override
	public Employe showEmployeDetails(int idE) {
		Employe employe = em.find(Employe.class, idE);
		if (employe != null)
			return employe;
		return null;
	}

	@Override
	public int registerEmployee(Employe employe, int idC) {

		Company company = em.find(Company.class, idC);
		employe.setCompany(company);
		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		employe.setConfirmationToken(generatedString);
		String hashpass = BCrypt.hashpw(employe.getPassword(), BCrypt.gensalt());
		employe.setPassword(hashpass);
		try {

			em.persist(employe);
			String Subject = "Verify your Account ! ";
			String message = "Hi, \r\n" + "Please verify your account by clicking the link below: \r\n"
					+ "http://localhost:9080/webops-web/rest/employee/" + employe.getId() + "/" + generatedString + "";
			mailService.Send(null, null, employe.getEmail(), Subject, message);
			return employe.getId();
		} catch (PersistenceException | MessagingException | UnsupportedEncodingException e) {
			return 0;
		}

	}

	@Override
	public Company registerCompany(Company company, int idA) {

		Employe employe = em.find(Employe.class, idA);
		if (employe.getCompany() == null) {
			em.persist(company);
			employe.setCompany(company);
			return company;
		}
		return null;
	}

	@Override
	public int editEmployee(Employe employe, int idE) {

		Employe emp = em.find(Employe.class, idE);
		if (emp != null) {
			employe.setId(emp.getId());
			String hashpass = BCrypt.hashpw(employe.getPassword(), BCrypt.gensalt());
			employe.setPassword(hashpass);
			em.merge(employe);
			return employe.getId();
		}
		return 0;
	}

	@Override
	public void removeEmployee(int idE) {
		Employe employe = em.find(Employe.class, idE);
		try {
			em.remove(employe);
		} catch (PersistenceException e) {
		}
	}

	@Override
	public void editCompany(int idC, Company company) {

		Employe emp = em.find(Employe.class, idC);
		int idCom = emp.getCompany().getId();
		Company com = em.find(Company.class, idCom);
		if (com != null) {
			company.setId(com.getId());
			em.merge(company);
		}
	}

	@Override
	public void removeCompany(int idC) {

		Employe empl = em.find(Employe.class, idC);
		int idCom = empl.getCompany().getId();
		Company company = em.find(Company.class, idCom);
		if (company != null) {
			try {

				for (Employe emp : company.getEmployes()) {
					em.remove(emp);
				}
				em.remove(company);
			} catch (PersistenceException e) {
			}
		}

	}

	@Override
	public Company showCompanyDetails(int idE) {
		Employe employe = em.find(Employe.class, idE);
		if (employe != null) {
			return employe.getCompany();
		}
=======
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
>>>>>>> webops-ejb/src/main/java/services/EmployeeServiceImpl.java
		return null;
	}

	@Override
<<<<<<< webops-ejb/src/main/java/services/EmployeeServiceImpl.java
	public void addImageCompany(int idA, String imageName) {

		Employe employe = em.find(Employe.class, idA);
		if (employe != null) {
			Company company = employe.getCompany();
			company.setName(imageName);
		}

	}

	@Override
	public void addImageEmployee(int idE, String imageName) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIndexResult(Company company) {
		Settings settings = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		IndexResponse response = client.prepareIndex("companies", "company", String.valueOf(company.getId()))
				.setSource(new Gson().toJson(company), XContentType.JSON).get();
		client.close();
		return response.getResult().toString();
=======
	public void addEmployeeToCompany(int idEmp, int idCompany) {
		
		Employe emp = em.find(Employe.class, idEmp);
		Company company = em.find(Company.class, idCompany);
		emp.setCompany(company);
		
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

	@Override
	public int addCompany(Company company) {
		em.persist(company);
		return company.getId();
	}

	@Override
	public Set<Employe> showCompanyEmployees(int idC) {
		Company company = em.find(Company.class, idC);
		return company.getEmployes();
	}

	@Override
	public List<Company> showCompanies() {
		List<Company> companies = 	em.createQuery("from Company", Company.class).getResultList();
		return companies;
>>>>>>> webops-ejb/src/main/java/services/EmployeeServiceImpl.java
	}

}
