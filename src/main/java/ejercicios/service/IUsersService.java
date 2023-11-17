package ejercicios.service;

import java.util.List;

import ejercicios.dto.Users;

public interface IUsersService {
	
	List<Users> getUsers();
	
	Users userPerId(Long userId);
	
	Users saveUser(Users userId);
	
	Users updateUser(Users userId);
	
	void deleteUser(Long userId);

}
