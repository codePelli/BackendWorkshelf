package ejercicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Role;
import ejercicios.service.RoleServiceImpl;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleServiceImpl RoleService;
	
	@GetMapping
	public List<Role> getAllRoles(){
		
		return RoleService.getRoles();
	}
	
	@GetMapping("/{id}")
	public Role RolePerId(@PathVariable Long id) {
		
		return RoleService.RolePerId(id);
	}
	
	@PostMapping("/add")
	public Role insertRole(@RequestBody Role Role) {
		
		return RoleService.updateRole(Role);
	}
	
	@PutMapping("/{id}")
	public Role updateRole(@PathVariable(name = "id") Long id, @RequestBody Role Role) {
		
		Role RoleSelected = new Role();
		
		RoleSelected.setRoleName(Role.getRoleName());
		
		return RoleSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable Long id) {
		RoleService.deleteRole(id);
	}
}

