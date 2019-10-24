package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Company;
import entities.Employe;
import entities.User;

@Remote
public interface EmployeeServiceRemote {

	public int addAdmin(Employe emp , int idCompany);
	public int addEmployee(Employe emp , int idCompany);
	public int disableEmployee(int idE);
	public int confirmAccountEmployee(int idE , String token);
	public int editEmployeeAccount(Employe emp , int idE);
	public Employe showEmployeeDetails(int idE);
	
}
