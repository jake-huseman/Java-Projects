package gamedr.Moderator;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gamedr.MatchPair.MatchPair;
import gamedr.MatchPair.MatchPairRepository;
import gamedr.Matchmaker.Matchmaker;
import gamedr.Users.Game;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class controls the Moderator class.
 * @author David Dong
 *
 */

@Api(value = "ModeratorController", tags = "moderator-controller")
@RestController
@RequestMapping("/moderator")
public class ModeratorController {

	/**
	 * String to show a Users actions were successful.
	 */
    private String success = "{\"message\":\"success\"}";

	/**
	 * String to show a Users actions were not successful.
	 */
    private String failure = "{\"message\":\"failure\"}";

	@Autowired
	ModeratorRepository moderatorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * List all Moderators
	 */
	@ApiOperation(value = "List all moderators", response = Moderator.class, tags = "moderator-controller")
	@GetMapping
	List<Moderator> getModerators() {
		return moderatorRepository.findAll();
	}
	
    /**
     * Creates Moderator for User
     */
    @ApiOperation(value = "Creates Moderator for user", response = Moderator.class, tags = "moderator-controller")
    @PostMapping("/{username}/{description}")
    Moderator createModerator(@PathVariable String username, @PathVariable String description) {
    	User user = userRepository.findByUsername(username);
    	Moderator moderator = new Moderator(user, description);
    	moderatorRepository.save(moderator);
    	user.setModerator(moderator);
    	userRepository.save(user);
    	return moderator;
    }
	
	
}
