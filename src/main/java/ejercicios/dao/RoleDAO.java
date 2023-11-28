package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
