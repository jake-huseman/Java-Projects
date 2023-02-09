package gamedr.Rejection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gamedr.Profile.Profile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class controls the Rejection class.
 * @author David Dong
 *
 */

@Api(value = "RejectionController", tags = "rejection-controller")
@RestController
@RequestMapping("/rejection")
public class RejectionController {

	/**
	 * Standard String displaying successful request
	 */
    private String success = "{\"message\":\"success\"}";

	/**
	 * Standard String displaying failed request
	 */
    private String failure = "{\"message\":\"failure\"}";
	
	@Autowired
	RejectionRepository rejectionRepository;
	
	@Autowired
	RejectionService rejectionService;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * GET request listing all Rejection objects.
	 * @return List of Rejections
	 */
	@ApiOperation(value = "Get all Rejections from the database", response = Rejection.class, tags = "rejection-controller")
	@GetMapping(path = "")
	public List<Rejection> getAllRejections() {
		return rejectionService.getAllRejections();
	}

	/**
	 * GET request 
	 * @return List of Rejections
	 */
	@ApiOperation(value = "Get all pairs of rejected Users, excluding the Rejection object itself", response = Rejection.class, tags = "rejection-controller")
	@GetMapping(path = "/user")
	public List<Set<User>> getAllRejectionsByUser() {
		return rejectionService.getAllRejectionsByUser();
	}

	/**
	 * Gets all Rejections with the User
	 * @return List of Rejections
	 */
	@ApiOperation(value = "Get all Rejections along with both User objects", response = Rejection.class, tags = "rejection-controller")
	@GetMapping(path = "/user/withRejections")
	public List<RejectionUserMerge> getAllRejectionsWithUser() {
		return rejectionService.getAllRejectionsWithUser();
	}

	/**
	 * Updates a Rejection.
	 * @param user1_id
	 * @param user2_id
	 * @param isPermanent
	 * @return success if updated, failure if null
	 */
	@ApiOperation(value = "Post Rejection to datebase by HTTP request (not JSON)", response = Rejection.class, tags = "rejection-controller")
	@PostMapping(path = "")
	public String postRejection(
		@RequestParam int user1_id,
		@RequestParam int user2_id,
		@RequestParam boolean isPermanent
	) {
		User user1 = userRepository.findById(user1_id);
		User user2 = userRepository.findById(user2_id);
		if (user1 == null || user2 == null)
			return failure;
		rejectionService.registerRejection(user1, user2, isPermanent);
		return success;
	}
	
}
