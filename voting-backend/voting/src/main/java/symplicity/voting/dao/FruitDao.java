package symplicity.voting.dao;


import java.util.List;

import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitNotFoundException;

public interface FruitDao {
	
	Fruit createFruit(Fruit fruit);
	List<Fruit> findAllFruits();
	Fruit findFruitByName(String name);
	Fruit findFruitById(String id);
	Fruit updateFruit(Fruit fruit);
	void updateVote(Fruit fruit);
	void deleteFruit(String fruitID) throws FruitNotFoundException;

}
