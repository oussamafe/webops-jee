package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Company;
import entities.Employe;
import entities.User;

@Remote
public interface EmployeeServiceRemote {

	public int addEmployee(Employe emp);
	public boolean editEmployee(Employe emp , int idEmp);
	public Employe showEmployeeDetails(int idEmp);
	public void addEmployeeToCompany(int idEmp , int idCompany);
	public void removeEmployeeFromCompany(int idEmp , int idComp);
	public List<User> showAllUsers();
	public int addCompany(Company company);
	public Set<Employe> showCompanyEmployees(int idC);
	public List<Company> showCompanies();
	
}
