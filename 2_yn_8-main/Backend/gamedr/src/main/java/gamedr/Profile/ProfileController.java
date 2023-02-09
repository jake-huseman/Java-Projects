package gamedr.Profile;

import io.swagger.annotations.Api;
//import gamedr.Post.PostForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import gamedr.Users.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class controls the Profile for the User.
 * @author Jake Huseman
 */

@Api(value = "ProfileController", tags = "profile-controller")
@RestController
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    /**
     * String to show a Users actions were successful.
     */
    private String success = "{\"message\":\"success\"}";

    /**
     * String to show a Users actions were not successful.
     */
    private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all Profiles.
     * @return A List of all Profiles
     */
    @ApiOperation(value = "Get all Profiles from the database ", response = Profile.class, tags = "profile-controller")
    @GetMapping(path = "/profiles")
    List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    /**
     * Gets a Profile by its id.
     * @param id
     * @return Profile
     */
    @ApiOperation(value = "Get Profile by Id", response = Profile.class, tags = "profile-controller")
    @GetMapping(path = "/profiles/{id}")
    Profile getProfileById(@PathVariable int id){
        return profileRepository.findById(id);
    }

    /**
     * Creates a Profile.
     * @param Profile
     * @return success if successful, failure if null
     */
    @ApiOperation(value = "Create a new Profile ", response = Profile.class, tags = "profile-controller")
    @PostMapping(path = "/createProfile")
    String createProfile(@RequestBody Profile Profile){
        if (Profile == null)
            return failure;
        profileRepository.save(Profile);
        return success;
    }

    /**
     * Updates a profile by its id.
     * @param id
     * @param request
     * @return updated Profile
     */
    @ApiOperation(value = "Update a Profile in the database ", response = Profile.class, tags = "profile-controller")
    @PutMapping(path = "/profiles/{id}")
    Profile updateProfile(@PathVariable int id, @RequestBody Profile request){
        Profile profile = profileRepository.findById(id);
        if(profile == null)
            return null;
        profileRepository.save(request);
        return profileRepository.findById(id);
    }

    /**
     * Deletes Profile by its id.
     * @param id
     * @return success
     */
    @ApiOperation(value = "Delete all Profiles from the database ", response = Profile.class, tags = "profile-controller")
    @DeleteMapping(path = "/profiles/{id}")
    String deleteProfiles(@PathVariable int id){
        profileRepository.deleteById(id);
        return success;
    }
}