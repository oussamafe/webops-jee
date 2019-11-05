package services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import entities.Candidate;
import entities.Company;
import entities.Course;
import entities.JobOffer;
import entities.ProfessionalExperience;
import interfaces.CandidateInterfaceRemote;

@Stateless
@LocalBean
public class CandidateService implements CandidateInterfaceRemote {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;


	@Override
	public int addCandidate(Candidate C) {

		if(ValidateMail(C.getEmail())==0) {
			em.persist(C);
			String pass = BCrypt.hashpw(C.getPassword(), BCrypt.gensalt());
			C.setPassword(pass);
			return C.getId();
		}
		return 0 ;
	}


	public Long ValidateMail(String Email) {
		TypedQuery<Long> query = em.createQuery("select count(*) from Candidate e where e.email=:Cemail", Long.class);
		query.setParameter("Cemail",Email);
		Long nbr=query.getSingleResult();
		return nbr;

	}
	// à verifier avec email unique
	@Override
	public int UpdateEmailCandidate(int idC,String Email) {


		if(ValidateMail(Email)==0) {
			Query query2 = em.createQuery("update Candidate C set C.email=:Email where C.id=:Candidateid");
			query2.setParameter("Email", Email);
			query2.setParameter("Candidateid", idC);
			int modified = query2.executeUpdate();
			return modified;

		} return 0;

	}


	@Override
	public void UpdateCandidate(Candidate C	) {
		Candidate candidate = em.find(Candidate.class, C.getId()); 
		candidate.setFirst_Name(C.getFirst_Name()); 
		candidate.setLast_Name(C.getLast_Name());
		candidate.setExperiences(C.getExperiences());
		candidate.setStudyLevel(C.getStudyLevel());
		candidate.setSkills(C.getSkills());
		candidate.setProfilIntro(C.getProfilIntro());
		candidate.setPhoneNumber(C.getPhoneNumber());
		candidate.setCertifications(C.getCertifications());

	}
	//methode facultative
	@Override
	public void RemoveCandidate(int idC) {
		em.remove(em.find(Candidate.class, idC));


	}

	@Override
	public Candidate displayCandidateDetails(int idC) {
		Candidate candidate = em.find(Candidate.class, idC);
		if(candidate != null)
			return candidate;
		return null;
	}

	@Override
	public void affectCourseCandidate(int CourseId, int CandidateId) {
		Candidate CandidateManagedEntity = em.find(Candidate.class, CandidateId);
		Course CourseManagedEntity = em.find(Course.class, CourseId);
		CourseManagedEntity.setCandidate(CandidateManagedEntity);



	}



	@Override
	public Set<Course> getAllCourseBycandidate(int CandidateId) {
		Candidate candidateManagedEntity = em.find(Candidate.class, CandidateId);
		Set<Course> Courses = new HashSet<Course>();
		for(Course C : candidateManagedEntity.getCourses()){
			Courses.add(C);

		}

		return Courses;
	}

	@Override
	public void affectProExpCandidate(int ProfExpId, int CandidateId) {
		Candidate CandidateManagedEntity = em.find(Candidate.class, CandidateId);
		ProfessionalExperience ProfessionalExperienceManagedEntity = em.find(ProfessionalExperience.class, ProfExpId);
		ProfessionalExperienceManagedEntity.setCandidate(CandidateManagedEntity);

	}


	@Override
	public Set<ProfessionalExperience> getAllExpProdBycandidate(int CandidateId) {
		Candidate candidateManagedEntity = em.find(Candidate.class, CandidateId);
		Set<ProfessionalExperience> ExpProf = new HashSet<ProfessionalExperience>();
		for(ProfessionalExperience e : candidateManagedEntity.getProfessionalExperiences()){
			ExpProf.add(e);
		}

		return ExpProf;

	}


	@Override
	public void ToSubScribetoCandidate(int idCandidate, int idSub) {
		Query query = em.createQuery("update Candidate C set C.SubCand=CONCAT(IFNULL(C.SubCand,''),:id) where C.id=:Candidateid");
		Query query2 = em.createQuery("update Candidate C set C.SubbedCand=CONCAT(IFNULL(C.SubbedCand,''),:id) where C.id=:idSub");
		
		query.setParameter("id", idSub+"|");
		query.setParameter("Candidateid", idCandidate);
		query.executeUpdate();
		query2.setParameter("id", idCandidate+"|");
		query2.setParameter("idSub", idSub);
		query2.executeUpdate();

	}


	@Override
	public void ToSubScribetoCompany(int idCandidate, int idSubComp) {
		Query query = em.createQuery("update Candidate C set C.SubCompany=concat(IFNULL(C.SubCompany,''),:id) where C.id=:Candidateid");
		query.setParameter("id", idSubComp+"|");
		query.setParameter("Candidateid", idCandidate);
		query.executeUpdate();

	}

	@Override
	public void ToRemoveCandidateSub(int idCandidate, int idSub) {
		Query query = em.createQuery("Select C.SubCand from Candidate C where id="+idCandidate);
		Query query2 = em.createQuery("Select C.SubbedCand from Candidate C where id="+idSub);
		String AllResquets = (String) query.getSingleResult();
		String AllResquets2 = (String) query2.getSingleResult();
		String[] array =AllResquets.split("\\|");
		String[] array2 =AllResquets2.split("\\|");
		List<String> list = convertArrayToList(array);
		List<Integer> listOfInteger = convertStringListToIntList( list,Integer::parseInt); 
		List<String> list2 = convertArrayToList(array2);
		List<Integer> listOfInteger2 = convertStringListToIntList( list2,Integer::parseInt); 
		
		String str="";
		String str2="";
		for (int i = 0; i < listOfInteger.size(); i++) {
			if(listOfInteger.get(i)==idSub) {
				listOfInteger.remove(i);
			}else
			
				str=str+listOfInteger.get(i)+"|";
			
			}
			
		for (int i = 0; i < listOfInteger2.size(); i++) {
			if(listOfInteger2.get(i)==idCandidate) {
				listOfInteger2.remove(i);
			}
			else
				str2=str2+listOfInteger2.get(i)+"|";
			
			
			}
			Query query1 = em.createQuery("update Candidate C set C.SubCand='"+str+"' where C.id="+idCandidate);
			query1.executeUpdate();
			Query query22 = em.createQuery("update Candidate C set C.SubbedCand='"+str2+"' where C.id="+idSub);
			query22.executeUpdate();
			
		
	}
	
	
	
//pas encore tester le webservice correspondant

	@Override
	public List<String> getAllMyCompanySub(int idCompany) {
		Query query = em.createQuery("Select C.SubCompany from Candidate C where id="+idCompany);
		String AllIdSub = (String) query.getSingleResult();
		String[] array =AllIdSub.split("\\|");
		List<String> list = convertArrayToList(array); 
		List<Integer> listOfInteger = convertStringListToIntList( list,Integer::parseInt); 
		List<String> ALLSubNames =new ArrayList<String>();
		for (int i = 0; i < listOfInteger.size(); i++) {
			
			Query query2 = em.createQuery("Select C from Company C where C.id="+listOfInteger.get(i));
			Company A=(Company) query2.getSingleResult();
			ALLSubNames.add(A.getName());
			System.out.println(ALLSubNames);
			}
		return ALLSubNames;
		}
	//pas encore tester le webservice correspondant
	
	
		@Override
		public List<String> getAllMyCandidateSubs(int idCandidate) {
			Query query = em.createQuery("Select C.SubCand from Candidate C where id="+idCandidate);
			String AllIdSub = (String) query.getSingleResult();
			String[] array =AllIdSub.split("\\|");
			List<String> list = convertArrayToList(array); 
			List<Integer> listOfInteger = convertStringListToIntList( list,Integer::parseInt); 
			List<String> ALLSubNames =new ArrayList<String>();
			for (int i = 0; i < listOfInteger.size(); i++) {
				
				Query query2 = em.createQuery("Select C from Candidate C where C.id="+listOfInteger.get(i));
				Candidate A=(Candidate) query2.getSingleResult();
				ALLSubNames.add(A.getFirst_Name()+" " +A.getLast_Name());
				System.out.println(ALLSubNames);
				}
			return ALLSubNames;
		} 
	@Override
	public List<String> getAllMysubscribers(int idCandidate) {
		Query query = em.createQuery("Select C.SubbedCand from Candidate C where id="+idCandidate);
		String AllIdSub = (String) query.getSingleResult();
		String[] array =AllIdSub.split("\\|");
		List<String> list = convertArrayToList(array); 
		List<Integer> listOfInteger = convertStringListToIntList( list,Integer::parseInt); 
		List<String> ALLSubNames =new ArrayList<String>();
		for (int i = 0; i < listOfInteger.size(); i++) {
			
			Query query2 = em.createQuery("Select C from Candidate C where C.id="+listOfInteger.get(i));
			Candidate A=(Candidate) query2.getSingleResult();
			ALLSubNames.add(A.getFirst_Name()+" " +A.getLast_Name());
			System.out.println(ALLSubNames);
			}
		return ALLSubNames;
	}
	
	//Useful methode 
	 public static <T> List<T> convertArrayToList(T array[]) 
	    { 
	  
	        // Create an empty List 
	        List<T> list = new ArrayList<>(); 
	  
	        // Iterate through the array 
	        for (T t : array) { 
	            // Add each element into the list 
	            list.add(t); 
	        } 
	  
	        // Return the converted List 
	        return list; 
	    }
	//Useful methode
	 public static <T, U> List<U> 
	    convertStringListToIntList(List<T> listOfString, 
	                               Function<T, U> function) 
	    { 
	        return listOfString.stream() 
	            .map(function) 
	            .collect(Collectors.toList()); 
	    }
	 //not tested as web service
	




	@Override
	public void ToRemoveCompanySub(int idCandidate, int idCompany) {
		Query query = em.createQuery("Select C.SubCompany from Candidate C where id="+idCandidate);
		String AllResquets = (String) query.getSingleResult();
		String[] array =AllResquets.split("\\|");
		List<String> list = convertArrayToList(array);
		List<Integer> listOfInteger = convertStringListToIntList( list,Integer::parseInt); 
		System.out.println(listOfInteger);
		String str="";
		for (int i = 0; i < listOfInteger.size(); i++) {
			System.out.println(listOfInteger.get(i));
			if(listOfInteger.get(i)==idCompany) {
				listOfInteger.remove(i);
				
			}else
				
				str=str+listOfInteger.get(i)+"|";
				System.out.println(str);
			
			}
		Query query1 = em.createQuery("update Candidate C set C.SubCompany='"+str+"' where C.id="+idCandidate);
			query1.executeUpdate();
		
	}


	@Override
	public List<Candidate> SearchCandidateSkills(String str) {
		TypedQuery<Candidate> query= em.createQuery(
				"select  C from Candidate C where C.first_Name LIKE :str or C.last_Name LIKE :str ", 
				Candidate.class);
		query.setParameter("str", "%"+str+"%");
		return query.getResultList();

	}


	@Override
	public List<Company> SearchCompanyByNameField(String str) {
		TypedQuery<Company> query= em.createQuery(
				"select  C from Company C where C.name Like :str or C.field LIKE :str", 
				Company.class);
		query.setParameter("str", "%"+str+"%");
				return query.getResultList();
	}


	@Override
	public List<JobOffer> SearchJobOfferMultipe(String str) {
		TypedQuery<JobOffer> query= em.createQuery(
				"select  C from JobOffer C where C.type LIKE :str or C.location LIKE :str or C.description LIKE :str", 
				JobOffer.class);
				query.setParameter("str", "%"+str+"%");
				
		return query.getResultList();
	}




	

}

