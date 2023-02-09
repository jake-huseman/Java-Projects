package gamedr.Mchron;

import java.util.Set;

import gamedr.Matchmaker.Matchmaker;
import gamedr.Users.User;

/**
 * This class facilitates sending both matches and users with the same HTTP request
 * @author David Dong
 *
 */
public class MatcherSeekersMerge {

	/**
	 * Matchmaker to hold Users.
	 */
	private Matchmaker matchmaker;

	/**
	 * Set of Users matched.
	 */
	private Set<User> matchedUsers;

	/**
	 * This method Constructs a set of matched Users and their Matchmaker.
	 * @param matchmaker
	 * @param matchedUsers
	 */
	public MatcherSeekersMerge(Matchmaker matchmaker, Set<User> matchedUsers) {
		this.matchmaker = matchmaker;
		this.matchedUsers = matchedUsers;
	}

	/**
	 * Public constructor with no params.
	 */
	public MatcherSeekersMerge() {}

	/**
	 * Gets Matchmaker.
	 * @return matchmaker
	 */
	public Matchmaker getMatchmaker() {
		return matchmaker;
	}

	/**
	 * Gets set of matched Users.
	 * @return matchedUsers
	 */
	public Set<User> getMatchedUsers() {
		return matchedUsers;
	}
	
}
