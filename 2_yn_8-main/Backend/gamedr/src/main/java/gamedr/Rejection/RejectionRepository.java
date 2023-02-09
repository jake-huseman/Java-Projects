package gamedr.Rejection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Repository for Rejection
 * @author David Dong
 *
 */

@Repository
public interface RejectionRepository extends JpaRepository<Rejection, Integer> {
	
	Rejection findById(int id);
	
}
