package coms309.people;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;

/**
 * Controller used to showcase Create and Read from a LIST
 *
 * @author Vivek Bengre
 */

@RestController
public class PeopleController {

    // Note that there is only ONE instance of PeopleController in 
    // Springboot system.
    HashMap<String, Person> peopleList = new HashMap<>();

    //CRUDL (create/read/update/delete/list)
    // use POST, GET, PUT, DELETE, GET methods for CRUDL

    // THIS IS THE LIST OPERATION
    // gets all the people in the list and returns it in JSON format
    // This controller takes no input. 
    // Springboot automatically converts the list to JSON format 
    // in this case because of @ResponseBody
    // Note: To LIST, we use the GET method
    @GetMapping("/people")
    public @ResponseBody HashMap<String,Person> getAllPersons() {
        return peopleList;
    }

    // THIS IS THE CREATE OPERATION
    // springboot automatically converts JSON input into a person object and 
    // the method below enters it into the list.
    // It returns a string message in THIS example.
    // in this case because of @ResponseBody
    // Note: To CREATE we use POST method
    @PostMapping("/people")
    public @ResponseBody String createPerson(@RequestBody Person person) {
        System.out.println(person);
        peopleList.put(person.getUsername(), person);
        return "New person "+ person.getUsername() + " Saved";
    }

    // THIS IS THE READ OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We extract the person from the HashMap.
    // springboot automatically converts Person to JSON format when we return it
    // in this case because of @ResponseBody
    // Note: To READ we use GET method
    @GetMapping("/people/{username}")
    public @ResponseBody Person getPerson(
    		@PathVariable String username,
    		@RequestParam(value = "password", required = true) String password
    ) {
        Person p = peopleList.get(username);
        if(password.equals(p.getPassword()))
        	return p;
        else
        	return null;
    }

    // THIS IS THE UPDATE OPERATION
    // We extract the person from the HashMap and modify it.
    // Springboot automatically converts the Person to JSON format
    // Springboot gets the PATHVARIABLE from the URL
    // Here we are returning what we sent to the method
    // in this case because of @ResponseBody
    // Note: To UPDATE we use PUT method
    
    @PutMapping("/people/update1/{username}")
    public @ResponseBody Person updatePerson(
    		@PathVariable String username,
    		@RequestParam(value = "password", required = true) String password,
    		@RequestBody Person p
    ) {
    	if(password.equals(peopleList.get(username).getPassword())) {
	        peopleList.replace(username, p);
	        username = p.getUsername();
	        return peopleList.get(username);
    	}
    	else
    		return null;
    }
    
    // Second UPDATE operation
    
    @PutMapping("/people/{username}")
    public @ResponseBody Person updateField(
    		@PathVariable String username,
    		@RequestParam(value = "field") String field,
    		@RequestParam(value = "fieldValue") String fieldValue,
    		@RequestParam(value = "password", required = true) String password
    ) {
    	Person p = peopleList.get(username);
    	if(password.equals(p.getPassword())) {
	        if(field.equals("username"))
	        	p.setUsername(fieldValue);
	        else if(field.equals("password"))
	        	p.setPassword(fieldValue);
	        else if(field.equals("age"))
	        	p.setAge(Integer.parseInt(fieldValue));
	        else if(field.equals("game"))
	        	p.setGame(fieldValue);
	        return p;
    	}
    	else
    		return null;
    }

    // THIS IS THE DELETE OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We return the entire list -- converted to JSON
    // in this case because of @ResponseBody
    // Note: To DELETE we use delete method
    
    @DeleteMapping("/people/{username}")
    public @ResponseBody HashMap<String, Person> deletePerson(
    		@PathVariable String username,
    		@RequestParam(value = "password", required = true) String password
    ) {
    	Person p = peopleList.get(username);
    	if(password.equals(p.getPassword())) {
	        peopleList.remove(username);
	        return peopleList;
    	}
    	else
    		return null;
    }
}

