package interfaces;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface UserInterfaceRemote {

	public User login(String username , String password);
	public void setRefreshToken(int idU , String refreshToken);
	public boolean checkRefreshToken(int idU , String token);
}
