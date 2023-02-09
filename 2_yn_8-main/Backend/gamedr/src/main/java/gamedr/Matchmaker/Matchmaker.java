package gamedr.Matchmaker;

import gamedr.Mchron.Mchron;
import gamedr.Users.User;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class emulates a Matchmaker and contains properties specific to a User who is a Matchmaker.
 * @author David Dong
 *
 */

@Entity
@Table(name = "Matchmaker")
public class Matchmaker
{
    /**
     * An id to access the Chronicle by.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * A Matchmakers rating.
     */
    @Column(nullable = false)
    private int rating;

    /**
     * A Matchmakers Description.
     */
    @Column(nullable = false)
    private String descrip;

    /**
     * The User who is the current Matchmaker.
     */
    @OneToOne(mappedBy = "matchmaker")
    @JsonIgnore
    private User user;

    /**
     *
     */
    @OneToMany(mappedBy = "matchmaker")
    private Set<Mchron> mchrons;

    /**
     * This method constructs a new Matchmaker
     * @param rating
     * @param user
     * @param descrip
     */
    public Matchmaker(int rating, User user, String descrip) {//, String desc) {//, User user) {
        this.rating = rating;
		this.descrip = descrip;
        this.user = user;
    }

    /**
     * Public constructor with no Params.
     */
    public Matchmaker() {}

    /**
     * Gets description of Matchmaker.
     * @return descrip
     */
	public String getDescrip() {return descrip;}

    /**
     * Sets description of Matchmaker.
     * @param descrip
     */
	public void setDesc(String descrip) {this.descrip = descrip;}

    /**
     * Gets rating of Matchmaker.
     * @return rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating of Matchmaker.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the User of the Matchmaker.
     * @return user
     */
    public User getUser() {return user;}

    /**
     * Sets the User of the Matchmaker.
     * @param user
     */
    public void setUser(User user) {this.user = user;}

    /**
     * Gets set of Mchrons.
     * @return mchrons
     */
    public Set<Mchron> getMchrons() {
    	return mchrons;
    }

    /**
     * Adds a mchron to the Set.
     * @param mchron
     */
    public void addMchron(Mchron mchron) {
    	this.mchrons.add(mchron);
    }

    /**
     * Sets the set of mchrons
     * @param mchrons
     */
    public void setMchrons(Set<Mchron> mchrons) {
    	this.mchrons = mchrons;
    }
}
