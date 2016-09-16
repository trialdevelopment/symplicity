package symplicity.voting.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import symplicity.voting.dao.UserDao;
import symplicity.voting.entity.User;
import symplicity.voting.exception.UserAlreadyExistsException;
import symplicity.voting.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public User findUserById(String id) throws UserNotFoundException {
		User user =  dao.findUserById(id);
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
	}

	@Override
	public User findUser(String email,String password) throws UserNotFoundException {
		User user = dao.findUser(email,password);
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
	}
	
	@Override
	public User findUserByEmail(String email) throws UserNotFoundException {
		User user = dao.findUserByEmail(email);
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
	}

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		User existing =  dao.findUserByEmail(user.getEmail());
		if(existing == null) {
			return dao.createUser(user);
		}
		else {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public User updateUser(String id, User user) throws UserNotFoundException {
		User existing =  dao.findUserById(id);
		if(existing == null) {
			throw new UserNotFoundException();
		}
		else {
			return dao.updateUser(user);
		}
	}

	@Override
	public void deleteUser(String id) throws UserNotFoundException {
		dao.deleteUser(id);
	}
	
}
