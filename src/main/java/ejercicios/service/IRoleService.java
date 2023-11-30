package ejercicios.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ejercicios.dto.Role;
import ejercicios.dto.User;

public interface IRoleService {
	
	public List<Role> getRoles();

	public Role RolePerId(Long id);

	public Role saveRole(Role Role);

	public Role updateRole(Role Role);

	public void deleteRole(Long id);
	
	public List<User> getUsersByRoleName(String roleName);
	
    Page<Role> getPaginatedRole(Pageable pageable);

}
