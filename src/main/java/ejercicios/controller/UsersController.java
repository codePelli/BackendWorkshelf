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

import ejercicios.dto.Users;
import ejercicios.service.IUsersService;
import ejercicios.service.UsersServiceImpl;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersServiceImpl userService;

	@GetMapping
	public List<Users> getAllUsers(){
		
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public Users userPerId(@PathVariable Long id) {
		
		return userService.userPerId(id);
	}
	
	@PostMapping("/add")
	public Users insertUser(@RequestBody Users user) {
		
		return userService.updateUser(user);
	}
	
	@PutMapping("/{id}")
	public Users updateUser(@PathVariable(name = "id") Long id, @RequestBody Users user) {
		
		Users userSelected = new Users();
		
		userSelected.setUsername(user.getUsername());
		userSelected.setUserPassword(user.getUserPassword());
		userSelected.setEmail(user.getEmail());
		userSelected.setUserRole(user.getUserRole());
		
		return userSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
