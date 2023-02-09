package gamedr.Mchron;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gamedr.MatchPair.MatchPair;
import gamedr.MatchPair.MatchPairRepository;
import gamedr.Matchmaker.Matchmaker;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class controls the Mchron class.
 * @author David Dong
 *
 */

@Api(value = "MchronController", tags = "mchron-controller")
@RestController
@RequestMapping("/mchronicle")
public class MchronController {

	/**
	 * String to show a Users actions were successful.
	 */
    private String success = "{\"message\":\"success\"}";

	/**
	 * String to show a Users actions were not successful.
	 */
    private String failure = "{\"message\":\"failure\"}";

	@Autowired
	MchronRepository mchronRepository;
	
	@Autowired
	MchronService mchronService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MatchPairRepository matchPairRepository;

	/**
	 * Gets all Mchrons
	 * @return List of Mchrons
	 */
	@ApiOperation(value = "Get all Mchrons from the database", response = Mchron.class, tags = "mchron-controller")
	@GetMapping(path = "")
	public List<Mchron> getAllMchrons() {
		return mchronRepository.findAll();
	}

	/**
	 * Gets all Mchrons by User id.
	 * @param user_id
	 * @return Set of Mchrons
	 */
	@ApiOperation(value = "Get Mchron by User ID, for the User who owns the Mchron", response = Mchron.class, tags = "mchron-controller")
	@GetMapping(path = "/{user_id}")
	public Set<Mchron> getAllMchronsByUserId(@PathVariable int user_id) {
		return mchronService.getAllMchronsByUserId(user_id);
	}

	/**
	 * Gets Matchers merged with Seekers.
	 * @return List of Matchers and Seekers
	 */
	@ApiOperation(value = "Get Mchron object with the Matcher who owns the Mchron and both Seekers which the Mchron describes", response = Mchron.class, tags = "mchron-controller")
	@GetMapping(path = "/users")
	public List<MatcherSeekersMerge> getMatcherSeekersMerged() {
		return mchronService.getMatcherSeekersMerged();
	}

	/**
	 * Makes a Mchron
	 * @param matcher_id
	 * @param matchPair_id
	 * @param rating
	 * @return success if successful, failure if null
	 */
	@ApiOperation(value = "Update Mchron through HTTP request (not JSON)", response = Mchron.class, tags = "mchron-controller")
	@PostMapping(path = "")
	public String postMchron(
		@RequestParam int matcher_id,
		@RequestParam int matchPair_id,
		@RequestParam int rating
	) {
		User user = userRepository.findById(matcher_id);
		Matchmaker matcher = user.getMatchmaker();
		MatchPair matchPair = matchPairRepository.findById(matchPair_id);
		if (matcher == null || matchPair == null)
			return failure;
		mchronService.registerMchron(matcher, matchPair, rating);
		return success;
	}
	
}
