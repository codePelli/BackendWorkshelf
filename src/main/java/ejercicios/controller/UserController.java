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
import ejercicios.security.LibraryUserDetails;
import ejercicios.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	//For getting user token
	private LibraryUserDetails getToken;
	
	//ONLY ADMIN USE
	@GetMapping("/all")
	public List<User> getAllUsers(){	
		return userService.getUsers();
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<User> userPerId(@PathVariable Long id) {
		if (getToken.getUserToken().getUserId().equals(id)) {	
			return new ResponseEntity<>(userService.userPerId(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	//ONLY ADMIN USE
	@PostMapping("/add")
	public User insertUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	//ONLY REGISTERED USER
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
		if (getToken.getUserToken().getUserId().equals(id)) {
			User userSelected = userService.userPerId(id);
			
			userSelected.setUsername(user.getUsername());
			userSelected.setPassword(user.getPassword());
			userSelected.setEmail(user.getEmail());
			userSelected.setRole(user.getRole());
			
	        return new ResponseEntity<>(userService.updateUser(userSelected), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		if (getToken.getUserToken().getUserId() == id) {
			userService.deleteUser(id);
		}
	}
	
	@GetMapping("/byUsername")
    public ResponseEntity<User> getByUsername(@RequestParam(name = "username") String username) {
		if (getToken.getUserToken().getUsername().equals(username)) {
			return new ResponseEntity<>(userService.userByUsername(username), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
    }
	
	//ONLY ADMIN USE
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<User>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<User> userPage = userService.getPaginatedProyectos(PageRequest.of(page, size));
        List<User> userDTOs = userPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
    
	@GetMapping("/byEmail")
    public ResponseEntity<User> byEmail(@RequestParam(name = "email") String email) {
		if (getToken.getUserToken().getEmail().equals(email)) {
			return new ResponseEntity<>(userService.getUser(email), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
    }

}
