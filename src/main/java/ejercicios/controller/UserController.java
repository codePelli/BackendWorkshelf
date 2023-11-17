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

import ejercicios.dto.User;
import ejercicios.service.IUserService;
import ejercicios.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public List<User> getAllUsers(){
		
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public User userPerId(@PathVariable Long id) {
		
		return userService.userPerId(id);
	}
	
	@PostMapping("/add")
	public User insertUser(@RequestBody User user) {
		
		return userService.updateUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
		
		User userSelected = new User();
		
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
