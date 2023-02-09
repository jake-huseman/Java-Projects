package gamedr.Profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import gamedr.Profile.ProfileRepository;
import javax.persistence.*;

/**
 * This class emulates a Profile which is linked to a User.
 * @author Jake Huseman
 */
@ApiModel
@Entity
@Table(name = "Profile")
public class Profile
{
    /**
     * id to identify the Profile by.
     */
	@ApiModelProperty("ID to identify Profile by")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Settings for the current User.
     */
    private String settings;

    /**
     * Preferences for the current User.
     */
    private String preferences;

    /**
     * Password of the current User.
     */
    private String password;
    //username

    /**
     * Users Profile.
     */
    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

    /**
     * This method constructs a new Profile for a User.
     * @param user
     * @param id
     * @param settings
     * @param preferences
     * @param password
     */
    public Profile(User user, int id, String settings, String preferences, String password)
    {
    	this.user = user;
    	this.id = id;
        this.settings = settings;
        this.preferences = preferences;
        this.password = password;
    }

    /**
     * Public constructor with no params.
     */
    public Profile() {}

    /**
     * Gets the id of the current Profile.
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the profiles id.
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the settings of the current Profile.
     * @return settings
     */
    public String getSettings(){
        return settings;
    }

    /**
     * Sets the current Profiles Settings.
     * @param settings
     */
    public void setSettings(String settings){
        this.settings = settings;
    }

    /**
     * Gets the Preferences for the current Profile.
     * @return preferences
     */
    public String getPreference(){
        return preferences;
    }

    /**
     * Sets the Preferences of the current Profile.
     * @param preferences
     */
    public void setPreferences(String preferences){
        this.preferences = preferences;
    }

    /**
     * Gets the password of the current Profile.
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Sets the password for the current Profile.
     * @param password
     */
    public void setPassword(String password) { this.password = password; }
}
