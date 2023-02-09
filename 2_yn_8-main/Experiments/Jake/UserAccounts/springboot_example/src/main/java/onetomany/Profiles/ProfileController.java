package onetomany.Profiles;

import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository laptopRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/profiles")
    List<Profile> getAllProfiles(){
        return ProfileRepository.findAll();
    }

    @GetMapping(path = "/profiles/{id}")
    Profile getProfileById(@PathVariable int id){
        return ProfileRepository.findById(id);
    }

    @PostMapping(path = "/profiles")
    String createProfile(@RequestBody Profile Profile){
        if (Profile == null)
            return failure;
        ProfileRepository.save(Profile);
        return success;
    }

    @PutMapping(path = "/profiles/{id}")
    Profile updateLaptop(@PathVariable int id, @RequestBody Profile request){
        Profile profile = ProfileRepository.findById(id);
        if(profile == null)
            return null;
        ProfileRepository.save(request);
        return ProfileRepository.findById(id);
    }

    @DeleteMapping(path = "/profiles/{id}")
    String deleteProfiles(@PathVariable int id){
        ProfileRepository.deleteById(id);
        return success;
    }
}
