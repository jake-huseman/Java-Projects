package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Person {

    private String username;

    private String password;

    private int age;

    private String game;

    public Person(){
        
    }

    public Person(String username, String password, int age, String game){
        this.username = username;
        this.password = password;
        this.age = age;
        this.game = game;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public String getGame() {
        return this.game;
    }

    public void setGame(String game) {
        this.game = game;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }

    @Override
    public String toString() {
        return username + " " 
               + age + " "
               + game;
    }
}
