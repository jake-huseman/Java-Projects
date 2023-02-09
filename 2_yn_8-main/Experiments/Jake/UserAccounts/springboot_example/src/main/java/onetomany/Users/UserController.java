package onetomany.Users;

import onetomany.Profiles.Profile;
import onetomany.Profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    ProfileRepository ProfileRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return UserRepository.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        UserRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = UserRepository.findById(id);
        if(user == null)
            return null;
        UserRepository.save(request);
        return UserRepository.findById(id);
    }   
    
    @PutMapping("/users/{userId}/profiles/{profileId}")
    String assignProfileToUser(@PathVariable int userId,@PathVariable int profileId){
        User user = UserRepository.findById(userId);
        Profile profile = ProfileRepository.findById(profileId);
        if(user == null || profile == null)
            return failure;
        profile.setUser(user);
        user.setProfile(profile);
        UserRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteProfile(@PathVariable int id){
        UserRepository.deleteById(id);
        return success;
    }
}
