package gamedr.Chronicle;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.Users.User;

/**
 * This class emulates a Chronicle, facilitating information about one seeker writes about another
 * @author David Dong
 */
@Entity
@Table(name = "Chronicle")
public class Chronicle {
	
	/**
	 * An ID by which to access the Chronicle
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * A set of Seekers, maximum size 2
	 */
	@ManyToMany(mappedBy = "chronicles")
	@JsonIgnore 
	private Set<Skr> skrs = new HashSet<>();
	
    /**
     * Date the report was created on.
     */
	@Column(nullable = false)
	private Date createdOn;
	
	/**
	 * Seeker personal rating of object Seeker interactions
	 */
	@Column(nullable = true)
	private int rating;
	
	/**
	 * Seeker personal description of object Seeker interactions
	 */
	@Column(nullable = true)
	private String descrip;
	
	/**
	 * This method constructs a new Chronicle.
	 * @param rating Seeker rating of interactions with other Seeker
	 * @param desc Seeker description of interactions with other Seeker
	 */
	public Chronicle(int rating, String descrip) {
		this.createdOn = new Date();
		this.rating = rating;
		this.descrip = descrip;
	}

	/**
	 * Public constructor with no Params.
	 */
	public Chronicle() {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		//this.chronicleDate = dtf.format(ZonedDateTime.now());
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public String getDescrip() {
		return descrip;
	}
	
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}


	/**
	 * Sets a Set of Seekers.
	 * @param skrs
	 */
	public void setSkrs(Set<Skr> skrs) {
		this.skrs = skrs;
	}

	/**
	 * Gets Set of Seekers.
	 * @return skrs
	 */
	public Set<Skr> getSkrs() {
		return skrs;
	}
	
	
}
