package gamedr.Rejection;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import gamedr.Users.User;

/**
 * This class facilitates sending both a User and Rejection with the same HTTP request.
 * @author David Dong
 *
 */

public class RejectionUserMerge {

	/**
	 * Set of Users.
	 */
	private Set<User> users;

	/**
	 * A Rejection Form.
	 */
	private Rejection rejection;

	/**
	 * This method constructs a Merge of a Rejection with a User.
	 * @param users
	 * @param rejection
	 */
	public RejectionUserMerge(Set<User> users, Rejection rejection) {
		this.users = users;
		this.rejection = rejection;
	}

	/**
	 * Public constructore with no params.
	 */
	public RejectionUserMerge() {}

	/**
	 * Gets a set of Users in the merge.
	 * @return
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Gets the current Rejection.
	 * @return rejection
	 */
	public Rejection getRejection() {
		return rejection;
	}
	
}
