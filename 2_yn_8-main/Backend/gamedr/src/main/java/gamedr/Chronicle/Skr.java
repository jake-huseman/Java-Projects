package gamedr.Chronicle;

import gamedr.MatchPair.MatchPair;
import gamedr.Users.User;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class emulates a Seeker.
 *
 * @author David Dong
 * @author Jake Huseman
 */
@Entity
@Table(name = "Skr")
public class Skr
{
    /**
     * An id to access the Chronicle by.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * A Seekers rating.
     */
    @Column(nullable = false)
    private int rating;

    /**
     * A Seekers Description.
     */
    @Column(nullable = false)
    private String descrip;

    /**
     * The User who is the current Seeker.
     */
    @OneToOne(mappedBy = "skr")
    @JsonIgnore
    private User user;

    /**
     * Set of Chronicles that the Seeker is apart of.
     */
    @ManyToMany
    @JoinTable(
    		name = "seeker_chronicles",
    		joinColumns = @JoinColumn(name = "skr_id"),
    		inverseJoinColumns = @JoinColumn(name = "chronicle_id")
    )
    private Set<Chronicle> chronicles = new HashSet<>();

    /**
     * This method constructs a new Seeker.
     * @param rating
     * @param user
     * @param descrip
     */
    public Skr(int rating, User user, String descrip) {//, String desc) {//, User user) {
        this.rating = rating;
		this.descrip = descrip;
        this.user = user;
    }

    /**
     * Public constructor with no Params.
     */
    public Skr() {}

    /**
     * Gets description of Seeker.
     * @return descrip
     */
	public String getDescrip() {return descrip;}

    /**
     * Sets description of Seeker.
     * @param descrip
     */
	public void setDesc(String descrip) {this.descrip = descrip;}

    /**
     * Gets rating of Seeker.
     * @return rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating of Seeker.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the User of the Seeker.
     * @return user
     */
    public User getUser() {return user;}

    /**
     * Sets the User of the Seeker.
     * @param user
     */
    public void setUser(User user) {this.user = user;}

    /**
     * Sets Chronicle for Seeker.
     * @param chronicles
     */
    public void setChronicles(Set<Chronicle> chronicles) {
    	this.chronicles = chronicles;
    }

    /**
     * Gets Set of Chronicles for Seeker.
     * @return chronicles
     */
    public Set<Chronicle> getChronicles() {
    	return chronicles;
    }
}
