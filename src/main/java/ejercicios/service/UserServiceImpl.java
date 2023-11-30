package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import ejercicios.dao.UserDAO;
import ejercicios.dto.User;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}

	@Override
	public User userPerId(Long userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId).get();
	}

	@Override
	public User saveUser(User userId) {
		// TODO Auto-generated method stub
		return userDAO.save(userId);
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
	public Page<User> getPaginatedProyectos(Pageable pageable) {
		// TODO Auto-generated method stub
		return userDAO.findAll(pageable);
	}

}
