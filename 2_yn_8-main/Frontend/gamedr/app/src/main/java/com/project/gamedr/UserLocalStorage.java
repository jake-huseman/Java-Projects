package com.project.gamedr;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Through Shared Preference stores input User object globally.
 * @author Sibhat Yusef
 */
public class UserLocalStorage {

    /**
     * String required to create new SharedPreference object.
     */
    public static final String SP_NAME = "userDetails";
    /**
     * Shared Preference variable to store User object globally.
     */
    SharedPreferences userLocalDatabase;

    /**
     * Set userLocalDatabase object to current input SharedPreference context.
     * @param context current context to make userLocalDatabase object.
     */
    public UserLocalStorage(Context context) {

        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    /**
     * Takes input User object in SharedPreference userLocalDatabase object.
     * @param user User object with information to store in database.
     */
    public void storeUser(User user) {

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("fullName", user.fullName);
        spEditor.putString("username", user.username);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("id", user.id);
        spEditor.putString("description", user.description);
        spEditor.putString("actor", user.actor);
        spEditor.commit();
    }

    /**
     * Get current User object that is stored in userLocalDatabase.
     * @return storedUser: current logged in user in the database.
     */
    public User getLoggedUser() {

        String fullName = userLocalDatabase.getString("fullName", "");
        String username = userLocalDatabase.getString("username", "");
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password", "");
        String id = userLocalDatabase.getString("id", "");
        String description = userLocalDatabase.getString("description", "");
        String actor = userLocalDatabase.getString("actor", "");

        //add id and description variables
        User storedUser = new User(fullName, username, email, password, id, description, actor);

        return storedUser;
    }

    /**
     * Take input boolean loggedIn to set Shared Preference boolean to true or false.
     * @param loggedIn boolean check if user is logged in.
     */
    public void setUserLoggedIn(boolean loggedIn) {

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    /**
     * Return boolean loggedIn to check if user is logged in or not.
     * @return boolean userLoggedIn
     */
    public boolean getUserLoggedIn() {

        return userLocalDatabase.getBoolean("loggedIn", false);
    }

    /**
     * Clears userLocalDatabase of current User object.
     */
    public void clearUser() {

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
