package ejercicios.service;

import java.util.List;

import ejercicios.dto.Role;

public interface IRoleService {
	
	public List<Role> getRoles();

	public Role RolePerId(Long id);

	public Role saveRole(Role Role);

	public Role updateRole(Role Role);

	public void deleteRole(Long id);

}
