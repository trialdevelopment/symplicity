package symplicity.voting.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitNotFoundException;

@Repository
public class FruitDaoImpl implements FruitDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Fruit> findAllFruits() {
		TypedQuery<Fruit> query = em.createQuery("SELECT m FROM Fruit m", Fruit.class);
    	List<Fruit> fruits = query.getResultList();
		return fruits;
	}
	
	@Override
	@Transactional
	public Fruit findFruitByName(String name) {
		TypedQuery<Fruit> query = em.createNamedQuery("Fruit.findByName", Fruit.class);
    	query.setParameter("pTitle", name);
    	List<Fruit> fruits = query.getResultList();
    	if(fruits != null && fruits.size() == 1) {
    		return fruits.get(0);
    	}
    	else {
			return null;
		}
	}
	
	@Override
	public Fruit findFruitById(String id) {
		return em.find(Fruit.class, id);
	}
	
	@Override
	@Transactional
	public Fruit createFruit(Fruit fruit) {
		
		em.persist(fruit);
		return fruit;
	}

	@Override
	@Transactional
	public Fruit updateFruit(Fruit fruit) {
		
		return em.merge(fruit);
	}

	@Override
	@Transactional
	public void deleteFruit(String fruitID) throws FruitNotFoundException{
		
		Fruit existing = findFruitById(fruitID);
		
		if(existing != null)
		{
			em.remove(existing);
		}
		else {
			throw new FruitNotFoundException();
		}
	}

	@Override
	@Transactional
	public void updateVote(Fruit fruit) {
		int vote;
		vote = (fruit.getVote()+1);
		
		Query query = em.createQuery("UPDATE Fruit m set m.vote =:pVote WHERE m.fruitID = :pFruitID");
    	query.setParameter("pVote", vote);
		query.setParameter("pFruitID", fruit.getFruitID());
		query.executeUpdate();
	}
	
	
}
