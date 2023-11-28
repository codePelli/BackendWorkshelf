package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.User;

public interface UserDAO extends JpaRepository<User,Long>{
	User findByUsername(String username);
}
