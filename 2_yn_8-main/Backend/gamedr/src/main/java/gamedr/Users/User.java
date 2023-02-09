package gamedr.Users;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.Chronicle.Skr;
//import gamedr.Chronicle.Chronicle;
import gamedr.MatchPair.MatchPair;
import gamedr.Matchmaker.Matchmaker;
import gamedr.Mchron.Mchron;
import gamedr.Moderator.Moderator;
//import gamedr.Post.PostForm;
import gamedr.Profile.Profile;
import gamedr.Rejection.Rejection;
import gamedr.Report.Report;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class emulates a User which interacts with the Matchmaking system and other Users.
 * @author David Dong
 * 
 */

@ApiModel
@Entity
@Table(name = "User")
public class User {

    /**
     * Primary key for User Entity
     */
	@ApiModelProperty(notes = "Primary key for User Entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/**
	 * If User has been banned
	 */
	@Column
	private boolean isBanned = false;

    /**
     * Official name of User
     */
	@ApiModelProperty(notes = "Official name of User")
    private String name;

    /**
     * Username of the User.
     */
	@Column(nullable = false)
    private String username;

    /**
     * Email of the User.
     */
    private String email;

    /**
     * Description of the current User.
     */
    private String description;
    
    /**
     * Password for User
     */
    @Column(nullable = true)
    private String password;

    /**
     * Actor settings associated with this User.
     */
    @Column(nullable = false)
    private Actor actor;

    /**
     * Type of game this User is interested in.
     */
    @Column(nullable = true)
    private Game game;

    /**
     * Seeker status of the user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skr_id", referencedColumnName = "id")
    private Skr skr;

    /**
     * Matchmaking status of the User.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "id")
    private Matchmaker matchmaker;

    /**
     * Moderator status of the current User.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "moderator_id", referencedColumnName = "id")
    private Moderator moderator;

    /**
     * Report status of the current User.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    /**
     * Profile linked to the User.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    /**
     * Set of rejections for the User.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Set<Rejection> rejections;
//
//    /**
//     * All Users Posts.
//     */
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(nullable = true)
//    private Set<PostForm> allUserPosts;

    /**
     * Included matchpairs for the User.
     */
    @ManyToMany
    @JoinTable(
    		name = "included_pairs",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "matchPair_id")
    )
    private Set<MatchPair> includedPairs = new HashSet<>();

    /**
     * This method constructs a new User.
     * @param name Official name
     * @param username Chosen username
     * @param email Email
     * @param description Personal description at account creation
     */
    public User(String name, String username, String email, String description, Actor actor) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.description = description;
        this.actor = actor;
        this.rejections = new HashSet<Rejection>();
    //    this.mchrons = new HashSet<Mchron>();
    }

    /**
     * Public constructor with no params.
     */
    public User() {}

    /**
     * Removes a matchpair from the User.
     * @param matchPair MatchPair object which is to be removed from includedPairs
     */
    public void removeInclusion(MatchPair matchPair) {
    	System.out.println(String.format("Attempting to remove pair with id %d", matchPair.getId()));
    	includedPairs.remove(matchPair);
    	//matchPair.getUsers().remove(this);
    }

    /**
     * Gets the id of the current User.
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the id of the current User.
     * @param id Set an ID for the User
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the name of the current User.
     * @return name The name of the user to be retrieved
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the current User.
     * @param name The given name of the User to be set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the email of the current User.
     * @return email The email of the User to be retrieved
     */
    public String getEmail(){
        return email;
	}

    /**
     * Gets the Users Username.
     * @return username The username of the User to be retrieved
     */
    public String getUsername() {
		return username;
	}

    /**
     * Sets the Users Username.
     * @param username The username of the User to be set
     */
    public void setUsername(String username){
    	this.username = username;
    }

    /**
     * Sets the email of the current User.
     * @param email The email of the User to be set
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Gets the description of the current User.
     * @return description The self-ascribed description of the User to be retrieved
     */
    public String getDescription() {
    	return description;
    }

    /**
     * Sets the current Description of the User.
     * @param description The self-ascribed description of the User to be set
     */
    public void setDescription(String description) {
    	this.description = description;
    }

    /**
     * Gets a set of included Matchpairs for the User.
     * @return includedPairs The matched pairs of which the User is a part to be retrieved
     */
    public Set<MatchPair> getInclusions() {
    	return includedPairs;
    }

    /**
     * Sets the included matchpairs.
     * @param matchPair The matched pairs of which the User is a part to be set
     */
    public void setInclusion(MatchPair matchPair) {
    	includedPairs.add(matchPair);
    }

    /**
     * Gets the Report for the current User.
     * @return report
     */
    public Report getReport() {
    	return report;
    }

    /**
     * Sets the reports for the current User.
     * @param report The Report to be set
     */
    public void setReport(Report report) {
    	this.report = report;
    }

    /**
     * Gets a set of Rejections for the User.
     * @return rejections
     */
    public Set<Rejection> getRejections() {
    	return rejections;
    }

    /**
     * Sets the Rejections for the User
     * @param rejections The Rejection objects of which the User is a part to be set
     */
    public void setRejections(Set<Rejection> rejections) {
    	this.rejections = rejections;
    }

    /**
     * Adds a Rejection to a User.
     * @param rejection The Rejection object of which the User is a part to be included in rejections
     */
    public void addRejection(Rejection rejection) {
    	this.rejections.add(rejection);
    }

    /**
     * Removes a Rejection from a User.
     * @param rejection The Rejection object to be removed from the rejections of a User
     */
    public void removeRejection(Rejection rejection) {
    	this.rejections.remove(rejection);
    }

    /**
     * Gets Users Seeker.
     * @return skr
     */
    public Skr getSkr() {
    	return skr;
    }

    /**
     * Sets the Users Seeker.
     * @param skr The Skr settings and descriptions to be set for a User
     */
    public void setSkr(Skr skr) {
    	this.skr = skr;
    }

    /**
     * Gets the Users Matchmaker.
     * @return matchmaker
     */
    public Matchmaker getMatchmaker() {
    	return matchmaker;
    }

    /**
     * Sets the Users Matchmaker
     * @param matchmaker The Matchmaker settings and descriptions to be set for a User
     */
    public void setMatchmaker(Matchmaker matchmaker) {
    	this.matchmaker = matchmaker;
    }

    /**
     * Gets the Users Moderator.
     * @return moderator
     */
    public Moderator getModerator() {
    	return moderator;
    }

    /**
     * Sets the Users moderator.
     * @param moderator The Moderator settings to be set for a User
     */
    public void setModerator(Moderator moderator) {
    	this.moderator = moderator;
    }

    /**
     * Gets the Users Actor.
     * @return actor
     */
    public Actor getActor() {
    	return actor;
    }

    /**
     * Sets the Users Actor.
     * @param actor The Actor (enum) to be set for a User
     */
    public void setActor(Actor actor) {
    	this.actor = actor;
    }

    /**
     * Gets the Users Game.
     * @return game
     */
    public Game getGame() {
    	return game;
    }

    /**
     * Sets the Users game.
     * @param game The Game (enum) to be set for a User
     */
    public void setGame(Game game) {
    	this.game = game;
    }
    
    /**
     * Get User password
     */
    public String getPassword() {
    	return password;
    }
    
    /**
     * Set User password
     */
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public boolean getIsBanned() {
    	return isBanned;
    }
    
    public void setBanned() {
    	isBanned = true;
    }
    
}
