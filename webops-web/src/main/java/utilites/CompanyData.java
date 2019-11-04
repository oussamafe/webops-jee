package utilites;

import com.fasterxml.jackson.annotation.JsonProperty;

import entities.Company;
import entities.Employe;

public class CompanyData {

	@JsonProperty("company")
	public Company company ;
	@JsonProperty("employee")
	public Employe employe;
	
	
	public CompanyData() {
		super();
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
}
