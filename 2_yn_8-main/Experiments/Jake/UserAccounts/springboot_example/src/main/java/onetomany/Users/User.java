package onetomany.Users;

import onetomany.Profiles.Profile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User
{
    /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private Date dateJoined;
    private boolean isActive;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User), the cascade option tells springboot
     * to create the child entity if not present already (in this case it is laptop)
     * @JoinColumn specifies the ownership of the key i.e. The User table will contain a foreign key from the laptop table and the column name will be laptop_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

     /*
     * @OneToMany tells springboot that one instance of User can map to multiple instances of Phone OR one user row can map to multiple rows of the phone table 
     */
    @OneToMany
    private List<User> users;

     // =============================== Constructors ================================== //


    public User(String name, String email, Date dateJoined) {
        this.name = name;
        this.email = email;
        this.dateJoined = dateJoined;
        this.isActive = true;
        users = new ArrayList<>();
    }

    public User() {
        users = new ArrayList<>();
    }

    
    // =============================== Getters and Setters for each field ================================== //


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){ this.email = email; }

    public Date getDateJoined(){
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined){
        this.dateJoined = dateJoined;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setIfActive(boolean isActive){
        this.isActive = isActive;
    }

    public Profile getProfile(){
        return profile;
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<User> getPhones() {
        return users;
    }

    public void setPhones(List<User> phones) {
        this.users = users;
    }

    public void addPhones(User user){ this.users.add(user); }
    
}
