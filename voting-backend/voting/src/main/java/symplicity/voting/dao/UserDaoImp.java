package symplicity.voting.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import symplicity.voting.entity.User;
import symplicity.voting.exception.UserNotFoundException;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.email ASC", User.class);
    	List<User> users = query.getResultList();
    	return users;
	}

	@Override
	public User findUserById(String id) {
		return em.find(User.class, id);
	}

	@Override
	public User findUser(String email,String password) {
		TypedQuery<User> query = em.createNamedQuery("User.findUser", User.class);
    	query.setParameter("pEmail", email);
    	query.setParameter("pPassword", password);
    	List<User> users = query.getResultList();
    	if(users != null && users.size() == 1) {
    		return users.get(0);
    	}
    	else {
    		return null;
    	}
	}
	
	@Override
	public User findUserByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
    	query.setParameter("pEmail", email);
    	List<User> users = query.getResultList();
    	if(users != null && users.size() == 1) {
    		return users.get(0);
    	}
    	else {
    		return null;
    	}
	}

	@Override
	@Transactional
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return em.merge(user);
	}

	@Override
	@Transactional
	public void deleteUser (String id) throws UserNotFoundException {
		User existing =  findUserById(id);
		if(existing == null) {
			throw new UserNotFoundException();
		}
		else {
			em.remove(existing);
		}
		
		
		Query query = em.createQuery(
			      "DELETE FROM User m WHERE m.id = :pID");
		query.setParameter("pID", id).executeUpdate();
		
	}

	
}
