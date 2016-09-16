package symplicity.voting.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import symplicity.voting.entity.Fruit;
import symplicity.voting.exception.FruitAlreadyExistsException;
import symplicity.voting.exception.FruitNotFoundException;
import symplicity.voting.service.FruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fruits")
@Api(tags="fruits")
public class FruitController {
	
	@Autowired
	private FruitService fruitService;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Find All fruits",
			notes="Returns a list of the fruits in the system.")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public List<Fruit> findAllFruits() {
		return fruitService.findAllFruits();
	}
	
	@RequestMapping(value="{name}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Get Fruit By Name",
			notes="Returns Fruit.")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public Fruit getFruit(@PathVariable("name") String name) throws FruitNotFoundException {
		return fruitService.findFruitByName(name);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Add Fruit",
	notes="Add and return Fruit")
	@ApiResponses(value={
		@ApiResponse(code=200, message="Success"),
		@ApiResponse(code=400, message="Bad Request"),
		@ApiResponse(code=500, message="Internal Server Error")
	})
	public Fruit createFruit(@RequestBody Fruit fruit) throws FruitAlreadyExistsException {
		
		return fruitService.createFruit(fruit);
	}
	
	@RequestMapping(value="{id}", 
			method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Update user Votes",
	notes="Update user votes for an existing Fruit")
	@ApiResponses(value={
		@ApiResponse(code=200, message="Success"),
		@ApiResponse(code=400, message="Bad Request"),
		@ApiResponse(code=404, message="Not Found"),
		@ApiResponse(code=500, message="Internal Server Error")
	})
	public void updateVote(@PathVariable("id") String id) throws FruitNotFoundException {
		fruitService.updateVote(id);
	}
	
	
	@RequestMapping(value="{id}", 
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Update Fruit",
	notes="Update an existing Fruit")
	@ApiResponses(value={
		@ApiResponse(code=200, message="Success"),
		@ApiResponse(code=400, message="Bad Request"),
		@ApiResponse(code=404, message="Not Found"),
		@ApiResponse(code=500, message="Internal Server Error")
	})
	public Fruit updateFruit (@PathVariable("id") String id, @RequestBody Fruit fruit) throws FruitNotFoundException, FruitAlreadyExistsException {
		return fruitService.updateFruit(id, fruit);
	}
	
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Delete Fruit",
	notes="Delete an existing Fruit")
	@ApiResponses(value={
		@ApiResponse(code=200, message="Success"),
		@ApiResponse(code=404, message="Not Found"),
		@ApiResponse(code=500, message="Internal Server Error")
	})
	public void deleteFruit (@PathVariable("id") String id) throws FruitNotFoundException {
		fruitService.deleteFruit(id);
	}

}
