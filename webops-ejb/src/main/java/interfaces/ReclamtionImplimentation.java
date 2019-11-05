package interfaces;

import java.util.List;  
import javax.ejb.Remote; 
import entities.Reclamation;

@Remote
public interface ReclamtionImplimentation {
	//CRUD
		
		public List<Reclamation> ReadClaim();
		public void UpdateClaim(Reclamation R);
		public void DeleteClaim(int id);
		
		public int ClaimUserToAdmin(Reclamation R); 
		
		public List<Reclamation> SuivieClaim(int Userid);
		public List<Reclamation> ShowClaimByEtat(String etat);
		public List<Reclamation> ShowClaimBySubject(String sujet);
		
		public List<Reclamation> ShowClaimByCompany(int idCompany);
		
		public void BestClaimTraiter();
		public void AffectClaimToTrait();
}
