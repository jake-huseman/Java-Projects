package gamedr.Users;

import java.util.List;

import gamedr.Report.Report;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gamedr.MatchPair.MatchPair;
import gamedr.MatchPair.MatchPairRepository;

/**
 * This class controls the User Class.
 * @author David Dong
 * @author Jake Huseman
 */ 

@Api(value = "UserController", tags = "user-controller")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MatchPairRepository matchPairRepository;

    /**
     * String to show a Users actions were successful.
     */
    private String success = "{\"message\":\"success\"}";

    /**
     * String to show a Users actions were not successful.
     */
    private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all Users.
     * @return List of Users
     */
    @ApiOperation(value = "Get all Users from the database", response = User.class, tags = "user-controller")
    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Get all Users Ascending
     * @param colname
     * @return List of Users
     */
    @ApiOperation(value = "Get all Users by ascending order alphabetically, given a property by which to sort ascendingly", response = User.class, tags = "user-controller")
    @GetMapping(path = "/users/ordered/{colname}/asc")
    List<User> getAllUsersOrderedAsc(@PathVariable("colname") @ApiParam(name = "colname", value = "User property by which to sort User list") String colname){
    	return userRepository.findAll(Sort.by(colname).ascending());
    }

    /**
     * Gets all users Descending.
     * @param colname
     * @return List of Users
     */
    @ApiOperation(value = "Get all Users by descending order alphabetically, given a property by which to sort descendingly", response = User.class, tags = "user-controller")
    @GetMapping(path = "/users/ordered/{colname}/des")
    List<User> getAllUsersOrderedDes(@PathVariable("colname") @ApiParam(name = "colname", value = "User property by which to sort User list") String colname){
    	return userRepository.findAll(Sort.by(colname).descending());
    }

    /**
     * Gets all Users in Ordered form.
     * @return List of Users
     */
    @ApiOperation(value = "Get all Users Ordered from the database by their name, alphabetically", response = User.class, tags = "user-controller")
    @GetMapping(path = "/users/ordered")
    List<User> getAllUsersOrdered(){
    	return userRepository.findAll(Sort.by("name").ascending());
    }
    
    /**
     * Get other Users matched with a User
     */
    @GetMapping(path = "/users/{user_id}/otherUsers")
    List<User> getOtherUsers(@PathVariable int user_id) {
    	User user = userRepository.findById(user_id);
    	return userService.matchedUsers(user);
    }

    /**
     * Gets User by id.
     * @param id
     * @return User
     */
    @ApiOperation(value = "Get User by User ID", response = User.class, tags = "user-controller")
    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    /**
     * Creates a new User.
     * @param user
     * @return success if saved, failure if null
     */
    @ApiOperation(value = "Create a new User by JSON request", response = User.class, tags = "user-controller")
    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }
    
    /**
     *  Registers a new User with password.
     *  @param username
     *  @param password
     *  @return success if saved, failure if null
     */
    @ApiOperation(value = "Register a new User with password by JSON request", response = User.class, tags = "user-controller")
    @PostMapping(path = "/users/register/{password}")
    String registerUser(@RequestBody User user, @PathVariable String password) {
    	if (user == null || userService.usernameExists(user.getUsername()))
    		return failure;
    	user.setPassword(password);
    	userRepository.save(user);
    	return success;
    }
    
    /**
     * Login functionality, given username and password
     * @param username
     * @param password
     * @return success if password matches username, failure if not
     */
    @ApiOperation(value = "Login an existing user", response = User.class, tags = "user-controller")
    @PostMapping(path = "/users/login/{username}/{password}")
    boolean loginUser(@PathVariable String username, @PathVariable String password) {
    	if (!userService.usernameExists(username) || !userService.passwordCorrect(username, password) || userRepository.findByUsername(username).getIsBanned()) {// || userService.userReportActive(username)) {
    		return false;
    	}
    	
    	return true;
    }

    /**
     * Updates a User by id.
     * @param id
     * @param request
     * @return updated User, null if update is null
     */
    @ApiOperation(value = "Updates a User given a User ID and a JSON request with new User properties", response = User.class, tags = "user-controller")
    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }
    
    /**
     * Remove User game tag
     * @return success or failure
     */
    @ApiOperation(value = "Updates a User's game tag", response = String.class, tags = "user-controller")
    @DeleteMapping("/users/game/{id}/del")
    String removeUserGame(@PathVariable int id) {
    	User user = userRepository.findById(id);
    	if (user == null)
    		return failure;
    	user.setGame(null);
    	userRepository.save(user);
    	
    	return success;
    }
    
    /**
     * Updates User Game
     */
    @ApiOperation(value = "Updates Game tag for user", response = User.class, tags = "user-controller")
    @PutMapping("/users/game/{id}/{game}")
    User updateUserGame(@PathVariable int id, @PathVariable Game game) {
    	User user = userRepository.findById(id);
    	user.setGame(game);
    	userRepository.save(user);
    	return userRepository.findById(id);
    }
    

    /**
     * Deletes all Users.
     * @return success
     */
    @ApiOperation(value = "Delete all Users from the database", response = User.class, tags = "user-controller")
    @DeleteMapping(path = "/users")
    String deleteAllUsers() {
    	userRepository.deleteAll();
    	return success;
    }

    /**
     * Deletes User by id
     * @param id
     * @return success
     */
    @ApiOperation(value = "Delete User by ID from the database", response = User.class, tags = "user-controller")
    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
}
