package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Reclamation;

@Remote
public interface ReclamtionImplimentation {
	//CRUD
		public int CreateClaim(Reclamation R);
		public List<Reclamation> ReadClaim();
		public void UpdateClaim(Reclamation R);
		public void DeleteClaim(int id);
}
