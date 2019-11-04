package interfaces;

import entities.User;

public interface UserInterfaceRemote {

	public User login(String username , String password);
}
