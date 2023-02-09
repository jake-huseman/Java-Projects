package gamedr.Moderator;

import gamedr.Mchron.Mchron;
import gamedr.Report.Report;
import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class emulates a Moderator with fields specific to Users who are Moderators.
 * @author David Dong
 *
 */

@ApiModel
@Entity
@Table(name = "Moderator")
public class Moderator
{
    /**
     * An id to identify the Moderator by.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Rating for the Moderator.
     */
	@ApiModelProperty(notes = "Number of succesful bans by Moderator")
    @Column(nullable = false)
    private int rating = 0;

    /**
     * The matchmaker for the Moderator.
     */
	@ApiModelProperty(notes = "Description of Moderator's self-prescribed goals", example = "I want to create a safe environment for Seekers.")
    @Column(nullable = false)
    private String descrip;

    /**
     * The User of the Moderator.
     */
    @OneToOne(mappedBy = "moderator", cascade=CascadeType.ALL)
    @JsonIgnore
    private User user;
    
    /**
     * This method constructs a new Moderator.
     * @param rating
     * @param user
     * @param descrip
     */
    public Moderator(User user, String descrip) {
		this.descrip = descrip;
        this.user = user;
    }

    /**
     * Public constructor with no params.
     */
    public Moderator() {}
    
    
    /**
     * Gets description of the Moderator.
     * @return descrip
     */
	public String getDescrip() {return descrip;}

    /**
     * Sets the description of the Moderator.
     * @param descrip
     */
	public void setDesc(String descrip) {this.descrip = descrip;}

    /**
     * Gets the Rating of the Moderator.
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the Rating of the Moderator.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the User of the Moderator.
     * @return user
     */
    public User getUser() {return user;}

    /**
     * Sets the User of the Moderator.
     * @param user
     */
    public void setUser(User user) {this.user = user;}
    
}
