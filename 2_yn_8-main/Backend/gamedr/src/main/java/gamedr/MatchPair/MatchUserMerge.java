package gamedr.MatchPair;

import java.util.Set;

import gamedr.Users.User;

/**
 * This class facilitates sending both matches and users with the same HTTP request
 * @author David Dong
 *
 */

public class MatchUserMerge {

	/**
	 * Set of users to merge.
	 */
	private Set<User> users;

	/**
	 * Match of users.
	 */
	private MatchPair match;

	/**
	 * This method constructs a Matchpair and a set of Users to merge.
	 * @param users
	 * @param match
	 */
	public MatchUserMerge(Set<User> users, MatchPair match) {
		this.users = users;
		this.match = match;
	}

	/**
	 * Public constructor with no params.
	 */
	public MatchUserMerge() {}

	/**
	 * Gets Users in set of Users.
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Gets the MatchPair.
	 * @return match
	 */
	public MatchPair getMatch() {
		return match;
	}
	
}
