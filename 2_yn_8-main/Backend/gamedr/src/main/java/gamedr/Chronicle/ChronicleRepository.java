package gamedr.Chronicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public interface ChronicleRepository extends JpaRepository<Chronicle, Long> {
    Chronicle findById(int id);

    @Transactional
    void deleteById(int id);
}
