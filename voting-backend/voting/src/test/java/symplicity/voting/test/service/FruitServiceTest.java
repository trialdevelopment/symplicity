package symplicity.voting.test.service;

import symplicity.voting.dao.FruitDao;
import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitAlreadyExistsException;
import symplicity.voting.exception.FruitNotFoundException;
import symplicity.voting.service.FruitService;
import symplicity.voting.service.FruitServiceImpl;
import symplicity.voting.test.TestConfig;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class FruitServiceTest {
	
	@Mock
	private FruitDao dao;
	
	@InjectMocks
	private FruitService service = new FruitServiceImpl();
	
	private Fruit fruit;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		fruit = new Fruit();
		fruit.setTitle("test");
//		movie.setImdb(imdb);
		fruit.setFruitID(UUID.randomUUID().toString());
	}
	
	@Test
	public void testFindAllFruits () {
		service.findAllFruits();
		Mockito.verify(dao).findAllFruits();
	}
	
	@Test
	public void testFindFruitByName () throws FruitNotFoundException {
		Mockito.when(dao.findFruitByName(fruit.getTitle())).thenReturn(fruit);
		
		Fruit actual = service.findFruitByName(fruit.getTitle());
		Assert.assertEquals(fruit, actual);
	}

	@Test(expected=FruitNotFoundException.class)
	public void testFindFruitByNameException () throws FruitNotFoundException {
		Mockito.when(dao.findFruitByName(fruit.getTitle())).thenReturn(null);
		
		service.findFruitByName(fruit.getTitle());
	}
	
	
	@Test(expected=FruitAlreadyExistsException.class)
	public void testCreateFruitException () throws FruitAlreadyExistsException {
		Mockito.when(dao.findFruitByName(fruit.getTitle())).thenReturn(fruit);
		service.createFruit(fruit);
	}
	
	
	@Test
	public void testCreateFruit() throws FruitAlreadyExistsException {
		Mockito.when(dao.findFruitByName(fruit.getTitle())).thenReturn(null);
		service.createFruit(fruit);
		Mockito.verify(dao).createFruit(fruit);
	}
	
	@Test(expected=FruitNotFoundException.class)
	public void testUpdateFruitException() throws FruitNotFoundException, FruitAlreadyExistsException {
		Mockito.when(dao.findFruitById(fruit.getFruitID())).thenReturn(null);
		service.updateFruit(fruit.getFruitID(),fruit);
	}
	
	
	@Test
	public void testDeleteFruit () throws FruitNotFoundException{
		service.deleteFruit(fruit.getFruitID());
		Mockito.verify(dao).deleteFruit(fruit.getFruitID());
	}
	
}
