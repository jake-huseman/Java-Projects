package gamedr.Chat;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * @author David Dong
 *
 */

public interface MessageRepository extends JpaRepository<Message, Long>{
	Message findById(int id);
}
