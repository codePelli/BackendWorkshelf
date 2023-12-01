package ejercicios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ejercicios.dao.UserDAO;
import ejercicios.dto.User;
import ejercicios.exception.UserAlreadyExistsException;
import ejercicios.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}
	

	@Override
	public User saveUser(User user) {
		Optional<User> theUser = userDAO.findByEmail(user.getEmail());
        if (theUser.isPresent()){
            throw new UserAlreadyExistsException("A user with " +user.getEmail() +" already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		// TODO Auto-generated method stub
		return userDAO.save(user);
	}
	
	@Override
	public User userPerId(Long userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId).get();
	}


	@Override
	public User updateUser(User userId) {
		// TODO Auto-generated method stub
		return userDAO.save(userId);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userDAO.deleteById(userId);
	}
	
	public User userByUsername(String username) {
        return userDAO.findByUsername(username);
    }
	
	@Override
    public User getUser(String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

	@Override
	public Page<User> getPaginatedProyectos(Pageable pageable) {
		// TODO Auto-generated method stub
		return userDAO.findAll(pageable);
	}

}
