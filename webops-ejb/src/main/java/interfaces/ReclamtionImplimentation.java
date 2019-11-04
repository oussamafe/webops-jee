package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Reclamation;

@Remote
public interface ReclamtionImplimentation {
	//CRUD
		public int CreateClaim(Reclamation R,int id);
		public List<Reclamation> ReadClaim();
		public void UpdateClaim(Reclamation R);
		public void DeleteClaim(int id);
		
	
		public List<Reclamation> SuivieClaim(int Userid);
		public List<Reclamation> ShowClaimByEtat(String etat);
		
		public void BestClaimTraiter();
		public void AffectClaimToTrait();
}
