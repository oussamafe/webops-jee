package interfaces;

import javax.ejb.Remote;

import entities.Company;
import entities.Employe;

@Remote
public interface EmployeeServiceRemote {
	
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
