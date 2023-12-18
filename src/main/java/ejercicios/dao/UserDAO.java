package ejercicios.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Rating;
import ejercicios.dto.Reservation;
import ejercicios.dto.User;

@Repository
public interface UserDAO extends JpaRepository<User,Long>{
	User findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	void deleteByEmail(String email);
}
