package gamedr.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Repository for Profile
 * @author Jake Huseman
 */

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findById(int id);
    void deleteById(int id);
}
