package gamedr.Rejection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class emulates a User who is Rejected.
 * @author David Dong
 *
 */

@ApiModel
@Entity
@Table(name = "Rejection")
public class Rejection {

	/**
	 * Primary key for Rejection Entity
	 */
	@ApiModelProperty(notes = "Primary key for Rejection Entity")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Date the rejection was created on.
	 */
	@Column(nullable = false)
	private Date createdOn;

	/**
	 * Boolean to determine if the Rejection is permanent or not.
	 */
	@Column(nullable = false)
	private boolean isPermanent;

	/**
	 * Set of Users.
	 */
	@ManyToMany
	@Column(nullable = true)
	@JsonIgnore
	private Set<User> users;

	/**
	 * This method constructs a Rejection and which Users are apart of it.
	 * @param isPermanent
	 * @param users
	 */
	public Rejection(boolean isPermanent, Set<User> users) {
		this.createdOn = new Date();
		this.isPermanent = isPermanent;
		this.users = users;
	}

	/**
	 * Public constructor with no params.
	 */
	public Rejection() {}

	/**
	 * Gets the id of the Rejection.
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets boolean value of the rejection Permanent status.
	 * @return
	 */
	public boolean getIsPermanent() {
		return isPermanent;
	}

	/**
	 * Sets the boolean value of Permanent status
	 * @param isPermanent
	 */
	public void setIsPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}

	/**
	 * Gets the Users in the rejection.
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}
	
}
