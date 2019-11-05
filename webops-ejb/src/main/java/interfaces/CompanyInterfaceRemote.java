package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Company;

@Remote
public interface CompanyInterfaceRemote {

	public Company registerCompany(Company company);
	public int removeComany(int idC);
	public List<Company> getAllCompanies();
}
