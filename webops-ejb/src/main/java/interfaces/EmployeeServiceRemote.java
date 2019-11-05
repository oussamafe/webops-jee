package interfaces;

import javax.ejb.Remote;

import entities.Company;
import entities.Employe;

@Remote
public interface EmployeeServiceRemote {
<<<<<<< webops-ejb/src/main/java/interfaces/EmployeeServiceRemote.java
=======

	public int addEmployee(Employe emp);
	public boolean editEmployee(Employe emp , int idEmp);
	public Employe showEmployeeDetails(int idEmp);
	public void addEmployeeToCompany(int idEmp , int idCompany);
	public void removeEmployeeFromCompany(int idEmp , int idComp);
	public List<User> showAllUsers();
	public int addCompany(Company company);
	public Set<Employe> showCompanyEmployees(int idC);
	public List<Company> showCompanies();
>>>>>>> webops-ejb/src/main/java/interfaces/EmployeeServiceRemote.java
	
	public int registerAdmin(Employe employe);
	public boolean activateAccount(int idE , String token);
	public Employe showEmployeDetails(int idE);
	public int registerEmployee(Employe employe , int idC);
	public Company registerCompany(Company company , int idA);
	public int editEmployee(Employe employe , int idE);
	public void removeEmployee(int idE);
	public void editCompany(int idC , Company company);
	public void removeCompany(int idC );
	public Company showCompanyDetails(int idE);
	public void addImageCompany(int idA , String imageName);
	public void addImageEmployee(int idE , String imageName);
	public String getIndexResult(Company company);
}
