package symplicity.voting.dao;


import java.util.List;

import symplicity.voting.entity.User;
import symplicity.voting.exception.UserNotFoundException;

public interface UserDao {

	public List<User> findAllUsers ();
	public User findUserById(String id);
	public User findUser(String email,String password);
	public User findUserByEmail(String email);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(String id) throws UserNotFoundException;
}
