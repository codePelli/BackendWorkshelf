package ejercicios.service;

import java.util.List;

import org.springframework.data.domain.*;

import ejercicios.dto.User;

public interface IUserService {
	
	List<User> getUsers();
	
	User userPerId(Long userId);
	
	User saveUser(User userId);
	
	User updateUser(User userId);
	
	void deleteUser(Long userId);

	User userByUsername(String username);
	
    Page<User> getPaginatedProyectos(Pageable pageable);

	User getUser(String email);
}
