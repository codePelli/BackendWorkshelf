package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Role;
import ejercicios.dto.User;
import ejercicios.service.RoleServiceImpl;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleServiceImpl RoleService;
	
	//ONLY ADMIN USE
	@GetMapping("/all")
	public List<Role> getAllRoles(){
		
		return RoleService.getRoles();
	}
	
	//ONLY ADMIN USE
	@GetMapping("/detail/{id}")
	public Role RolePerId(@PathVariable Long id) {
		
		return RoleService.RolePerId(id);
	}
	
	//ONLY ADMIN USE
	@PostMapping("/add")
	public Role insertRole(@RequestBody Role Role) {
		
		return RoleService.updateRole(Role);
	}
	
	//ONLY ADMIN USE
	@PutMapping("/update/{id}")
	public Role updateRole(@PathVariable(name = "id") Long id, @RequestBody Role Role) {
		
		Role RoleSelected = RoleService.RolePerId(id);
		
		RoleSelected.setRoleName(Role.getRoleName());
		
		return RoleService.updateRole(RoleSelected);
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/delete/{id}")
	public void deleteRole(@PathVariable Long id) {
		RoleService.deleteRole(id);
	}
	
	//ONLY ADMIN USE
	@GetMapping("/usersByRoleName")
    public List<User> getUsersByRoleName(@RequestParam(name = "roleName") String roleName) {
        return RoleService.getUsersByRoleName(roleName);
    }
	
	//ONLY ADMIN USE
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Role>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Role> rolePage = RoleService.getPaginatedRole(PageRequest.of(page, size));
        List<Role> pageDTOs = rolePage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
}

