package gamedr.MatchPair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;

//import gamedr.Category.CategoryForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class provides the REST API for the MatchPair Entity.
 * @author David Dong
 * 
 */ 

@Api(value = "MatchPairController", tags = "match-pair-controller")
@RestController
public class MatchPairController {
	
	private Random randomGenerator = new Random();
	
    @Autowired
    MatchPairRepository matchPairRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    MatchService matchService;

    /**
     * String to show a Users actions were successful.
     */
    private String success = "{\"message\":\"success\"}";

    /**
     * String to show a Users actions were not successful.
     */
    private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all MatchPairs.
     * @return List of MatchPairs
     */
    @ApiOperation(value = "Get all matchPairs from the database", response = MatchPair.class, tags = "match-pair-controller")
    @GetMapping(path = "/matchPairs")
    List<MatchPair> getAllMatchPairs(){
        return matchPairRepository.findAll();
    }

    /**
     * Gets all Matches with Users.
     * @return List of Matches with Users
     */
    @ApiOperation(value = "Get all matchPairs with User object", response = MatchPair.class, tags = "match-pair-controller")
    @GetMapping(path = "/matchPairs/users")
    List<MatchUserMerge> getAllMatchesWithUser() {
    	return matchService.getAllMatchesWithUser();
    }

    /**
     * Gets Matchpair by id
     * @param id
     * @return matchpair
     */
    @ApiOperation(value = "Get matchPair object by matchPair object ID", response = MatchPair.class, tags = "match-pair-controller")
    @GetMapping(path = "/matchPairs/{id}")
    MatchPair getMatchPairById(@PathVariable("id") @ApiParam(name = "id", value = "matchPair ID") int id){
        return matchPairRepository.findById(id);
    }

    /**
     * Gets Matched Users by id.
     * @param id
     * @return Set of Users in the MatchPair
     */
   @ApiOperation(value = "Get matchPair by matchPair object id from the database, and include both User objects", response = MatchPair.class, tags = "match-pair-controller")
    @GetMapping(path = "/matchPairs/{id}/users")
    Set<User> getMatchedUsersByPair(@PathVariable("id") Integer id){
    	MatchPair matchPair = matchPairRepository.findById(id);
    	System.out.println(matchPair.getUsers().size());
    	return matchPair.getUsers();
    }

    /**
     * Gets a Random MatchPair.
     * @return Users in MatchPair
     */
    @ApiOperation(value = "Get a random matchPair from the database", response = MatchPair.class, tags = "match-pair-controller")
    @GetMapping(path = "/matchPairs/random")
    Set<User> getRandomMatchPair() {
    	List<MatchPair> matchPairs = matchPairRepository.findAll();
    	int index = randomGenerator.nextInt(matchPairs.size());
    	return matchPairs.get(index).getUsers();
    }

    /**
     * Creates a Matchpair.
     * @param matchPair
     * @return success if successful, failure if null.
     */
    @ApiOperation(value = "Creates new matchPair by JSON request", response = MatchPair.class, tags = "match-pair-controller")
    @PostMapping(path = "/matchPairs")
    String createMatchPair(@RequestBody MatchPair matchPair){
        if (matchPair == null)
            return failure;
        matchPairRepository.save(matchPair);
        return success;
    }

    /**
     * Creates a MatchPair by two ids.
     * @param id1
     * @param id2
     * @return success if successful, failure if null
     */
    @ApiOperation(value = "Create matchPair by giving two User IDs", response = MatchPair.class, tags = "match-pair-controller")
    @PostMapping(path = "/matchPairs/{id1}/{id2}")
    String createMatchPairByIds(
    		@PathVariable("id1") Integer id1, 
    		@PathVariable("id2") Integer id2
    ) {
    	User user1 = userRepository.findById(id1);
    	User user2 = userRepository.findById(id2);
    	if (user1 == null || user2 == null)
    		return failure;
    	MatchPair matchedPair = new MatchPair(user1, user2);
    	matchPairRepository.save(matchedPair);
    	user1.setInclusion(matchedPair);
    	user2.setInclusion(matchedPair);
    	userRepository.save(user1);
    	userRepository.save(user2);
    	for (User user : matchedPair.getUsers()) {
    		System.out.println(user.getName());
    	}
    	System.out.println(matchedPair.getId());
    	return success;
    }

    /**
     * Updates a MatchPair by id.
     * @param id
     * @param request
     * @return updated MatchPair
     */
    @ApiOperation(value = "Updates a matchpair by providing ID and new MatchPair object", response = MatchPair.class, tags = "match-pair-controller")
    @PutMapping("/matchPairs/{id}")
    MatchPair updateMatchPair(@PathVariable int id, @RequestBody MatchPair request){
        MatchPair matchPair = matchPairRepository.findById(id);
        if(matchPair == null)
            return null;
        matchPairRepository.save(request);
        return matchPairRepository.findById(id);
    }

    /**
     * Deletes all MatchPairs
     * @return success
     */
    @ApiOperation(value = "Delete all matchPairs from the database", response = MatchPair.class, tags = "match-pair-controller")
    @DeleteMapping(path = "/matchPairs")
    String deleteAllMatchPairs() {
    	matchPairRepository.deleteAll();
    	return success;
    }

    /**
     * Deletes MatchPair by id
     * @param id
     * @return success
     */
    @ApiOperation(value = "Delete matchPairs from the database by ID of matchPair object", response = MatchPair.class, tags = "match-pair-controller")
    @DeleteMapping(path = "/matchPairs/{id}")
    String deleteMatchPair(@PathVariable int id){
        MatchPair matchPair = matchPairRepository.findById(id);
        for (Iterator<User> iterator = matchPair.getUsers().iterator(); iterator.hasNext();) {
            User user = iterator.next();
            System.out.println(user.getName());
            iterator.remove();
        }
        System.out.println(matchPair.getUsers().size());
        return success;
    }
   
}
