package gamedr.Report;

import java.util.Date;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamedr.Moderator.Moderator;
import gamedr.Users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class emulates a Report that is used to Report a User by.
 * @author David Dong
 *
 */

@ApiModel
@Entity
@Table(name = "Report")
public class Report {

    /**
     * Primary key for Report API
     */
	@ApiModelProperty(notes = "Primary key for Report API")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    /**
     * Date on which Report was created.
     */
	@ApiModelProperty(notes = "Date on which Report was created")
	@Column(nullable = false)
	private Date createdOn;

    /**
     * Moderator-given reason for Report registration
     */
	@ApiModelProperty(notes = "Moderator-given reason for Report registration", example = "Particularly obnoxious in global chat")
    @Column(nullable = true)
    private String reason;

    /**
     * The user which is Reported.
     */
    @OneToOne(mappedBy = "report")
    @JsonIgnore
    private User user;

    /**
     * This method constructs a new Report.
     * @param user
     * @param moderator
     * @param severity
     * @param reason
     */
    public Report(User user, String reason) {
    	this.createdOn = new Date();
    	this.reason = reason;
    	this.user = user;
    }

    /**
     * Public constructor with no params.
     */
    public Report() {}

    /**
     * Gets the id of the Report.
     * @return id
     */
    public int getId() {
    	return id;
    }

    /**
     * Gets the date the Report was created on.
     * @return createdOn
     */
    public Date getCreatedOn() {
    	return createdOn;
    }

    /**
     * Gets the Reason of the Report
     * @return reason
     */
    public String getReason() {
    	return reason;
    }

    /**
     * Sets the Description of the Report
     * @param reason
     */
    public void setDesc(String reason) {
    	this.reason = reason;
    }

    /**
     * Gets the Reported User.
     * @return user
     */
    public User getUser() {
    	return user;
    }
    
}
