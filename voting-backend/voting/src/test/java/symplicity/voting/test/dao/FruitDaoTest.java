package symplicity.voting.test.dao;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import symplicity.voting.dao.FruitDao;
import symplicity.voting.dao.FruitDaoImpl;
import symplicity.voting.entity.Fruit;
import symplicity.voting.test.TestConfig;

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
public class FruitDaoTest {
	
	@Mock
	private EntityManager em;
	
	@InjectMocks
	private FruitDao dao = new FruitDaoImpl();
	
	@Mock
	private TypedQuery<Fruit> query;
	
	private Fruit fruit;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		fruit = new Fruit();
		fruit.setTitle("test");
		fruit.setVote(5);
		fruit.setFruitID(UUID.randomUUID().toString());
	}
	
	@Test
	public void testFindAllFruits() {
		List<Fruit> expected = Arrays.asList(fruit);
		
		Mockito.when(em.createQuery("SELECT m FROM Fruit m", Fruit.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Fruit> fruits = dao.findAllFruits();
		Assert.assertEquals(expected, fruits);
	}
	
	@Test
	public void testFindFruitById() {
		Mockito.when(em.find(Fruit.class, fruit.getFruitID())).thenReturn(fruit);
		
		Fruit actual = dao.findFruitById(fruit.getFruitID());
		Assert.assertEquals(fruit, actual);
	}
	
	@Test
	public void testFindFruitByName () {
		List<Fruit> expected = Arrays.asList(fruit);
		Mockito.when(em.createNamedQuery("Fruit.findByName", Fruit.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		Fruit actual = dao.findFruitByName(fruit.getTitle());
		Assert.assertEquals(fruit, actual);
	}
	
	@Test
	public void testFindFruitByNameNull () {
		Mockito.when(em.createNamedQuery("Fruit.findByName", Fruit.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		
		Fruit actual = dao.findFruitByName(fruit.getTitle());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreateFruit () {
		dao.createFruit(fruit);
		Mockito.verify(em).persist(fruit);
	}
	
	@Test
	public void testUpdateFruit () {
		dao.updateFruit(fruit);
		Mockito.verify(em).merge(fruit);
	}
		
}
