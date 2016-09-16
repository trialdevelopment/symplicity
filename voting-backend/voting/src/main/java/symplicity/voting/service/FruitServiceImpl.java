package symplicity.voting.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import symplicity.voting.dao.FruitDao;
import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitAlreadyExistsException;
import symplicity.voting.exception.FruitNotFoundException;

@Service
public class FruitServiceImpl implements FruitService {
	
	@Autowired
	FruitDao fruitDao;
	
	@Override
	public List<Fruit> findAllFruits() {
		return fruitDao.findAllFruits();
	}

	@Override
	public Fruit createFruit(Fruit fruit) throws FruitAlreadyExistsException {
		
		Fruit existing = fruitDao.findFruitByName(fruit.getTitle());
		
		if(existing == null)
		{
			return fruitDao.createFruit(fruit);
		}
		
		else {
			
			throw new FruitAlreadyExistsException();
		}
		
	}

	@Override
	public Fruit updateFruit(String id, Fruit fruit) throws FruitNotFoundException, FruitAlreadyExistsException {
		Fruit existing = fruitDao.findFruitById(id);
		
		if(existing != null)
		{
			if(!existing.getTitle().equalsIgnoreCase(fruit.getTitle())){
				
				if(fruitDao.findFruitByName(fruit.getTitle())==null){
					return fruitDao.updateFruit(fruit);
				}
				else{
					throw new FruitAlreadyExistsException();
				}
			}
			else {
				return fruitDao.updateFruit(fruit);
			}
		}
		else {
			throw new FruitNotFoundException();
		}
	}

	@Override
	public void deleteFruit(String id) throws FruitNotFoundException {
		
		fruitDao.deleteFruit(id);
		
	}

	@Override
	public void updateVote(String id) throws FruitNotFoundException {
		Fruit existing = fruitDao.findFruitById(id);
		
		if(existing != null)
		{
			fruitDao.updateVote(existing);
		}
		
		else {
			throw new FruitNotFoundException();
		}
	}

	@Override
	public Fruit findFruitByName(String name) throws FruitNotFoundException {
		Fruit existing = fruitDao.findFruitByName(name);
		if(existing !=null){
			return fruitDao.findFruitByName(name);
		}
		else {
			throw new FruitNotFoundException();
		}
		
	}
	
	
}
