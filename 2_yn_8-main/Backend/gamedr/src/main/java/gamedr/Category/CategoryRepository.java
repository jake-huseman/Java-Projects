package gamedr.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository <CategoryForm, Integer>
{
    CategoryForm findById(int id);

    @Transactional
    void deleteById(int id);
}
