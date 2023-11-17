package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ejercicios.dao.UsersDAO;
import ejercicios.dto.Users;

public class UsersServiceImpl implements IUsersService{

	@Autowired
	UsersDAO userDAO;
	
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}

	@Override
	public Users userPerId(Long userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId).get();
	}

	@Override
	public Users saveUser(Users userId) {
		// TODO Auto-generated method stub
		return userDAO.save(userId);
	}

	@Override
	public Users updateUser(Users userId) {
		// TODO Auto-generated method stub
		return userDAO.save(userId);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userDAO.deleteById(userId);
	}

}
