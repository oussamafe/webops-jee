package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Reclamation;

@Remote
public interface ReclamtionImplimentation {
	//CRUD
		public int CreatePack(Reclamation R);
		public List<Reclamation> ReadPack(Reclamation R);
		public void UpdatePack(Reclamation R);
		public void DeletePack(Reclamation R);
}
