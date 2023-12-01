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

import ejercicios.dto.User;
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
		return userService.saveUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
		
		User userSelected = new User();
		
		userSelected.setUsername(user.getUsername());
		userSelected.setPassword(user.getPassword());
		userSelected.setEmail(user.getEmail());
		userSelected.setRole(user.getRole());
		
		return userSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/byUsername")
    public User getByUsername(@RequestParam(name = "username") String username) {
        return userService.userByUsername(username);
    }
	
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<User>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<User> userPage = userService.getPaginatedProyectos(PageRequest.of(page, size));
        List<User> userDTOs = userPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
}
