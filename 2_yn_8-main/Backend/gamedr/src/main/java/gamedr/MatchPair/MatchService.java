package gamedr.MatchPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamedr.Rejection.Rejection;
import gamedr.Rejection.RejectionUserMerge;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class services the matches.
 * @author David Dong
 *
 */
@Service
public class MatchService {
	
	@Autowired
	private MatchPairRepository matchRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<MatchUserMerge> getAllMatchesWithUser() {
		List<MatchUserMerge> ret = new ArrayList<MatchUserMerge>();
		List<MatchPair> matches = matchRepository.findAll();
		for (MatchPair match : matches) {
			Set<User> users = match.getUsers();
			MatchUserMerge merger = new MatchUserMerge(users, match);
			ret.add(merger);
		}
		return ret;
	}
	
}
