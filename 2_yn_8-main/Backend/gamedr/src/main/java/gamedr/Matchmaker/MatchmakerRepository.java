package gamedr.Matchmaker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * A Repository for Matchmaker
 * @author David Dong
 *
 */
@Repository
public interface MatchmakerRepository extends JpaRepository<Matchmaker, Integer> {
    Matchmaker findById(int id);

    @Transactional
    void deleteById(int id);
}
