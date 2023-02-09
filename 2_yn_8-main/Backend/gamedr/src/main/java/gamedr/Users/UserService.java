package gamedr.Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamedr.MatchPair.MatchPair;
import gamedr.Report.Report;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class services the User Class, an intermediary between the User Entity and User Repository.
 * @author David Dong
 *
 */

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * Checks if given username exists in database.
	 * @return if username exists
	 */
	public boolean usernameExists(String username) {
		boolean ret = false;
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			if (username.equals(user.getUsername())) {
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * @return other Users with whom a User has matched
	 */
	public List<User> matchedUsers(User user) {
		List<User> ret = new ArrayList<User>();
		for(MatchPair matchPair : user.getInclusions()) {
			for(User potentialUser : matchPair.getUsers()) {
				if(potentialUser.getId() != user.getId()) {
					ret.add(potentialUser);
				}
			}
		}
		
		return ret;
	}
	
//	/**
//	 * Checks if User has an active report
//	 * @return if User has an active report
//	 */
//	public boolean userReportActive(String username) {
//		User user = userRepository.findByUsername(username);
//		Report userReport = user.getReport();
//		if (userReport != null && userReport.getInEffect()) {
//			return true;
//		}
//		
//		return false;
//	}
//	
	
	/**
	 * Checks if given password matches given username.
	 * @return if password is correct
	 */
	public boolean passwordCorrect(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user.getPassword().equals(password))
			return true;
		
		return false;
	}

}
