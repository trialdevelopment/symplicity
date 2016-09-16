package symplicity.voting.service;



import java.util.List;

import symplicity.voting.entity.User;
import symplicity.voting.exception.UserAlreadyExistsException;
import symplicity.voting.exception.UserNotFoundException;

public interface UserService {

	List<User> findAllUsers();

	User findUserById(String id) throws UserNotFoundException;

	User findUser(String email,String password) throws UserNotFoundException;
	
	User findUserByEmail(String email) throws UserNotFoundException;

	User createUser(User user) throws UserAlreadyExistsException;

	User updateUser(String id, User user) throws UserNotFoundException;

	void deleteUser(String id) throws UserNotFoundException;

}
