package gamedr.Post;

import gamedr.Category.CategoryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostForm, Integer>
{
    PostForm findById(int id);

    void deleteById(int id);

    //List<PostForm> findAllByCategoryId(int id);

    List<PostForm> findAllByCategoryForm(CategoryForm categoryForm);
}
