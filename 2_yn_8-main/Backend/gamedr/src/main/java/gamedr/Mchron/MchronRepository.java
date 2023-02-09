package gamedr.Mchron;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * A Repository for Matchmaker's Chronicle (Mchron)
 * @author David Dong
 *
 */

@Repository
public interface MchronRepository extends JpaRepository<Mchron, Integer> {
	
	Mchron findById(int mchron_id);
	
}
