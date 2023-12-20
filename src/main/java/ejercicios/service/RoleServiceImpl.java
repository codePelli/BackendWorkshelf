package ejercicios.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.RoleDAO;
import ejercicios.dto.Role;
import ejercicios.dto.User;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	RoleDAO RolesDAO;

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return RolesDAO.findAll();
	}

	@Override
	public Role RolePerId(Long id) {
		// TODO Auto-generated method stub
		return RolesDAO.findById(id).get();
	}

	@Override
	public Role saveRole(Role id) {
		// TODO Auto-generated method stub
		return RolesDAO.save(id);
	}

	@Override
	public Role updateRole(Role id) {
		// TODO Auto-generated method stub
		return RolesDAO.save(id);
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		RolesDAO.deleteById(id);
	}
	
	public List<User> getUsersByRoleName(String roleName) {
        Role role = RolesDAO.findByRoleName(roleName);
        if (role != null) {
            return role.getUsers();
        }
        return Collections.emptyList();
    }

	@Override
	public Page<Role> getPaginatedRole(Pageable pageable) {
		// TODO Auto-generated method stub
		return RolesDAO.findAll(pageable);
	}
}
