package interfaces;

import javax.ejb.Remote;

import entities.Company;

@Remote
public interface CompanyInterfaceRemote {

	public int registerCompany(Company company);
	public int removeComany(int idC);
}
