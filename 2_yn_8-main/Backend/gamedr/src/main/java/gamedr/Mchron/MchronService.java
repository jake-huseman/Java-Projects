package gamedr.Mchron;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamedr.MatchPair.MatchPair;
import gamedr.Matchmaker.Matchmaker;
import gamedr.Matchmaker.MatchmakerRepository;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class services the Mchron.
 * @author David Dong
 *
 */
@Service
public class MchronService {

	@Autowired
	MchronRepository mchronRepository;
	
	@Autowired
	MatchmakerRepository matchmakerRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Gets Set of all Mchrons by Users id.
	 * @param user_id
	 * @return matchmaker.getMchrons();
	 */
	public Set<Mchron> getAllMchronsByUserId(int user_id) {
		User user = userRepository.findById(user_id);
		Matchmaker matchmaker = user.getMatchmaker();
		return matchmaker.getMchrons();
	}

	/**
	 * Gets List of all Matchers and Seekers.
	 * @return ret
	 */
	public List<MatcherSeekersMerge> getMatcherSeekersMerged() {
		List<MatcherSeekersMerge> ret = new ArrayList<MatcherSeekersMerge>();
		for (Mchron mchron : mchronRepository.findAll()) {
			MatchPair matchPair = mchron.getMatchPair();
			MatcherSeekersMerge merger = new MatcherSeekersMerge(mchron.getMatchmaker(), matchPair.getUsers());
			ret.add(merger);
		}
		
		return ret;
	}

	/**
	 * Registers a new Mchron.
	 * @param matchmaker
	 * @param matchPair
	 * @param rating
	 */
	public void registerMchron(Matchmaker matchmaker, MatchPair matchPair, int rating) {
		Mchron newMchron = new Mchron(matchmaker, matchPair, rating);
		mchronRepository.save(newMchron);
		matchmaker.addMchron(newMchron);
		matchmakerRepository.save(matchmaker);
	}
	
}
