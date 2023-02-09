package com.project.gamedr;
/**
 * User object to store globally in UserLocalStorage.
 * @author Sibhat Yusef
 */
public class User {

    /**
     * Store user's full name.
     */
    String fullName;
    /**
     * Store user's username.
     */
    String username;
    /**
     * Store user's email.
     */
    String email;
    /**
     * Store user's password.
     */
    String password;
    /**
     * Store user's id.
     */
    String id;
    /**
     * Store user's description.
     */
    String description;

    /**
     * Stores the users type, (seeker, moderator).
     */
    String actor;

    /**
     * Create new User Object with all fields.
     *
     */
    public User(String fullName, String username, String email, String password, String id, String description, String actor) {

        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.description = description;
        this.actor = actor;

    }

    /**
     * Create new User object with username and password fields.
     *
     */
    public User(String username, String password) {

        this.username = username;
        this.password = password;
        this.email  = "";
        this.fullName = "";
        this.id = "";
        this.description = "";
    }


}
