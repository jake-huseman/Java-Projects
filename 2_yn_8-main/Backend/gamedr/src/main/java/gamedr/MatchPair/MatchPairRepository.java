package gamedr.MatchPair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * A Repository for MatchPairs
 * @author David Dong
 * 
 */ 

@Repository
public interface MatchPairRepository extends JpaRepository<MatchPair, Long> {
    MatchPair findById(int id);

    @Transactional
    void deleteById(int id);
}
