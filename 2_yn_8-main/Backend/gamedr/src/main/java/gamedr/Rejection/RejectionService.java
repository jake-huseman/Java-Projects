package gamedr.Rejection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamedr.Report.Report;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class services the Rejection Class, an intermediary between the Rejection Entity and Rejection Repository.
 * @author David Dong
 *
 */

@Service
public class RejectionService {

	@Autowired
	RejectionRepository rejectionRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Gets all Rejections in Repository.
	 * @return rejectionRepository.findAll()
	 */
	public List<Rejection> getAllRejections() {
		return rejectionRepository.findAll();
	}

	/**
	 * Gets set of Rejections by Users.
	 * @return ret
	 */
	public List<Set<User>> getAllRejectionsByUser() {
		List<Set<User>> ret = new ArrayList<Set<User>>();
		List<Rejection> rejections = getAllRejections();
		for (Rejection rejection : rejections) {
			Set<User> users = rejection.getUsers();
			ret.add(users);
		}
		return ret;
	}

	/**
	 * Gets all Rejections with the User.
	 * @return ret
	 */
	public List<RejectionUserMerge> getAllRejectionsWithUser() {
		List<RejectionUserMerge> ret = new ArrayList<RejectionUserMerge>();
		List<Rejection> rejections = rejectionRepository.findAll();
		for (Rejection rejection : rejections) {
			Set<User> users = rejection.getUsers();
			RejectionUserMerge merger = new RejectionUserMerge(users, rejection);
			ret.add(merger);
		}
		return ret;
	}

	/**
	 * Registers a new Rejection.
	 * @param user1
	 * @param user2
	 * @param isPermanent
	 */
	public void registerRejection(User user1, User user2, boolean isPermanent) {
		Set<User> users = new HashSet<User>();
		users.add(user1);
		users.add(user2);
		Rejection newRejection = new Rejection(isPermanent, users);
		rejectionRepository.save(newRejection);
		user1.addRejection(newRejection);
		user2.addRejection(newRejection);
		userRepository.save(user1);
		userRepository.save(user2);
	}
	
}
