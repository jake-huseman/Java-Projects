package gamedr.MatchPair;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.Mchron.Mchron;
import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class emulates a MatchPair which is a match between two different Users and adds them to the set of Users.
 * @author David Dong
 *
 */

@ApiModel(description = "Matches two Users")
@Entity
@Table(name = "MatchPair")
public class MatchPair {

	/**
	 * Primary Key of MatchPair
	 */
	@ApiModelProperty(notes = "Primary key of MatchPair", example = "3")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ApiModelProperty(notes = "Date on which Users were matched", example = "2022/11/02 20:23:35")
	@Column(nullable = false)
	private String matchDate;

	/**
	 * Set of Users who are matched by MatchPair
	 */
	@ApiModelProperty(notes = "Set of Users who are matched by MatchPair")
	@ManyToMany(mappedBy = "includedPairs")//, cascade = CascadeType.REMOVE)
	@JsonIgnore // prevent infinite loop
	private Set<User> users = new HashSet<>();

	/**
	 * Set of Matchmaker's Chronicles to which MatchPair Entity belongs.
	 */
	@ApiModelProperty(notes = "Set of Matchmaker's Chronicles to which MatchPair Entity belongs")
	@OneToMany(mappedBy = "matchmaker", cascade = CascadeType.ALL)
    private Set<Mchron> mchrons;

	/**
	 * This method constructs a new pair of Matches.
	 * @param user1
	 * @param user2
	 */
	public MatchPair(User user1, User user2) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		this.matchDate = dtf.format(ZonedDateTime.now());
		
		users.add(user1);
		users.add(user2);
	}

	/**
	 * Public constructor with no Params. Creates a new HashSet of users.
	 */
	public MatchPair() {
		users = new HashSet<>();
	}

	/**
	 * Removes a User from the MatchPair.
	 * @param user
	 */
    public void removeUser(User user) {
    	this.users.remove(user);
    	user.getInclusions().remove(this);
    }

	/**
	 * Gets the id of the MatchPair.
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the date at which the MatchPair was created.
	 * @return matchDate
	 */
	public String getMatchDate() {
		return matchDate;
	}

	/**
	 * Sets the date at which the Users matched.
	 * @param matchDate
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * Gets Users in the Matchpair.
	 * @return users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Sets the Users in the MatchPair.
	 * @param users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * Sets the Mchrons for the Matchpair
	 * @param mchrons
	 */
	public void setMchrons(Set<Mchron> mchrons) {
		this.mchrons = mchrons;
	}

	/**
	 * Gets the set of Mchrons.
	 * @return mchrons
	 */
	public Set<Mchron> getMchrons() {
		return mchrons;
	}
	
}
