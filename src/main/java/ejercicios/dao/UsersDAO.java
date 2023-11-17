package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.Users;

public interface UsersDAO extends JpaRepository<Users,Long>{

}
