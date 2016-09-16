package symplicity.voting.test.controller;

import java.util.UUID;

import symplicity.voting.controller.FruitController;
import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitAlreadyExistsException;
import symplicity.voting.exception.FruitNotFoundException;
import symplicity.voting.service.FruitService;
import symplicity.voting.test.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class FruitControllerTest {
	
	    //mock the dependencies of the controller
		@Mock
		private FruitService service;

		//controller that needs to be tested
		@InjectMocks
		private FruitController controller;
			
		private MockMvc mockMvc;

		private Fruit fruit;
		
		private int vote;

		@Before
		public void setup() {
			//init mockito based mocks
			MockitoAnnotations.initMocks(this);
	
			vote =2;
			fruit = new Fruit();
			fruit.setTitle("test");
			
			fruit.setFruitID(UUID.randomUUID().toString());
			
			//init mockMvc with standalone setup for this controller test (used for unit test)
			//look at WebApplicationContext setup as well (mostly used for integration tests)
			mockMvc = MockMvcBuilders
							.standaloneSetup(controller)
							.build();
		}
		
		@Test
		public void testFindAllFruits() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/fruits"))
			.andExpect(MockMvcResultMatchers.status().isOk());

			Mockito.verify(service).findAllFruits();
		}

		@Test
		public void testGetFruit() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/fruits/" + fruit.getTitle()))
					.andExpect(MockMvcResultMatchers.status().isOk());
			Mockito.verify(service).findFruitByName(fruit.getTitle());
		}

		@Test
		public void testGetFruitNotFound() throws Exception {
			Mockito.when(service.findFruitByName("asdfasdf")).thenThrow(new FruitNotFoundException());
			
			mockMvc.perform(MockMvcRequestBuilders.get("/users/asdfasdf"))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
		}
		
		@Test
		public void testCreateFruit() throws Exception {
			//serialize to JSON string.
			//using Jackson's ObjectMapper to do that
			String requestBody = new ObjectMapper().writeValueAsString(fruit);
			
			mockMvc.perform(MockMvcRequestBuilders.post("/fruits").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(requestBody))
					.andExpect(MockMvcResultMatchers.status().isOk());
			
			Mockito.verify(service).createFruit(Mockito.any(Fruit.class));
		}
		
		@Test
		public void testCreateFruitException() throws Exception {
			Mockito.when(service.createFruit(Mockito.any(Fruit.class))).thenThrow(new FruitAlreadyExistsException());
			
			String requestBody = new ObjectMapper().writeValueAsString(fruit);
			mockMvc.perform(MockMvcRequestBuilders.post("/fruits")
												  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
												  .content(requestBody))
					.andExpect(MockMvcResultMatchers.status().isBadRequest());
		}
		
		@Test
		public void testUpdateFruit() throws Exception {
			String requestBody = new ObjectMapper().writeValueAsString(fruit);
			mockMvc.perform(MockMvcRequestBuilders.put("/fruits/" + fruit.getFruitID())
												  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
												  .content(requestBody))
					.andExpect(MockMvcResultMatchers.status().isOk());
			
			Mockito.verify(service).updateFruit(Mockito.eq(fruit.getFruitID()), Mockito.any(Fruit.class));
		}
		
		@Test
		public void testUpdateVote() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.put("/fruits/" + fruit.getFruitID()))
					.andExpect(MockMvcResultMatchers.status().isOk());
			
			Mockito.verify(service).updateVote(Mockito.eq(fruit.getFruitID()));
		}
		
		@Test
		public void testDeleteFruit() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.delete("/fruits/" + fruit.getFruitID()))
					.andExpect(MockMvcResultMatchers.status().isOk());
			Mockito.verify(service).deleteFruit(fruit.getFruitID());
		}
}
