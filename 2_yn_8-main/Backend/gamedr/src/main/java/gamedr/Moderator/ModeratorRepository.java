package gamedr.Moderator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gamedr.Users.User;

/**
 * 
 * A Repository for Moderator
 * @author David Dong
 *
 */

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
    Moderator findById(int id);

    @Transactional
    void deleteById(int id);
}
