package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Packs;

@Remote
public interface PackImplimentation {
	//CRUD
		public int CreatePack(Packs P);
		public List<Packs> ReadPack(Packs P);
		public void UpdatePack(Packs P);
		public void DeletePack(Packs P);

}
