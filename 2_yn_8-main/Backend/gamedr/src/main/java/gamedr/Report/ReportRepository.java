package gamedr.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamedr.Users.User;

/**
 * A Repository for Report.
 * @author David Dong
 *
 */

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	
	Report findByUser_Id(int user_id);
	
	Report findById(int id);
	
}
