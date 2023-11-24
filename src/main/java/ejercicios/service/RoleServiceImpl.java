package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ejercicios.dao.RoleDAO;
import ejercicios.dto.Role;

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

}
