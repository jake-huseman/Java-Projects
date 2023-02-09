package gamedr.Mchron;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.MatchPair.MatchPair;
import gamedr.Matchmaker.Matchmaker;
import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class emulates a Matchmaker's Chronicle.
 * @author David Dong
 *
 */

@ApiModel
@Entity
@Table(name = "Mchron")
public class Mchron {

	/**
	 * An id to identify the Mchron by.
	 */
	@ApiModelProperty(notes = "Primary key for Mchron Entity", example = "3")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * MatchPair of users paired.
	 */
	@ApiModelProperty(notes = "MatchPair which Mchron describes and rates")
	@OneToOne
	@JoinColumn(name = "matchPair_id", nullable=false)
	private MatchPair matchPair;

	/**
	 * Rating for the Mchron.
	 */
	@Column(nullable=false)
	@ApiModelProperty(notes = "Rating by Matchmaker for MatchPair")
	private int rating;

	/**
	 * The matchmaker for the Mchron
	 */
	@ManyToOne
	@JoinColumn(name="matchmaker_id", nullable=false)
	@JsonIgnore
	private Matchmaker matchmaker;

	/**
	 * This method constructs a Mchron.
	 * @param matchmaker
	 * @param matchPair
	 * @param rating
	 */
	public Mchron(Matchmaker matchmaker, MatchPair matchPair, int rating) {
		this.matchmaker = matchmaker;
		this.matchPair = matchPair;
		this.rating = rating;
	}

	/**
	 * Public constructor with no params.
	 */
	public Mchron() {}

	/**
	 * Gets the id of the Mchron.
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the matchmaker for the Mchron.
	 * @param matchmaker
	 */
	public void setMatchmaker(Matchmaker matchmaker) {
		this.matchmaker = matchmaker;
	}

	/**
	 * Gets the matchmaker for the Mchron.
	 * @return matchmaker
	 */
	public Matchmaker getMatchmaker() {
		return matchmaker;
	}

	/**
	 * Gets the User pair who are matched.
	 * @return matchpair
	 */
	public MatchPair getMatchPair() {
		return matchPair;
	}

	/**
	 * Gets the Rating of the Mchron.
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the Rating of the Mchron.
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
