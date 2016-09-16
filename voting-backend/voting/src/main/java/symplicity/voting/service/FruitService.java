package symplicity.voting.service;


import java.util.List;

import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitAlreadyExistsException;
import symplicity.voting.exception.FruitNotFoundException;

public interface FruitService {
	
	List<Fruit> findAllFruits();
	
	Fruit createFruit(Fruit fruit) throws FruitAlreadyExistsException;
	
	Fruit updateFruit(String id,Fruit fruit) throws FruitNotFoundException,FruitAlreadyExistsException;
	
	void updateVote(String id) throws FruitNotFoundException;
	
	Fruit findFruitByName(String name) throws FruitNotFoundException;
	
	void deleteFruit(String id) throws FruitNotFoundException;
	
}
