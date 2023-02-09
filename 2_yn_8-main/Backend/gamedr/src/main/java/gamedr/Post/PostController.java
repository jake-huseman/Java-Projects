package gamedr.Post;

import gamedr.Category.CategoryForm;
import gamedr.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController
{
    @Autowired
    PostsRepository postsRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @Autowired
    private CategoryRepository categoryRepository;

    //@GetMapping(path = "/Categories/{categoryId}") // how to save posts to categories.
    //List<PostForm> getAllPostsInCategory(@PathVariable("categoryId") int categoryId,) { return categoryRepository.findAll(); }

    @GetMapping(path = "/Categories/{categoryId}/Posts")
    List<PostForm> getAllPostsInCategory(@PathVariable("categoryId") int categoryId)
    {
        CategoryForm categoryForm = categoryRepository.findById(categoryId);
        return postsRepository.findAllByCategoryForm(categoryForm);
        //return postsRepository.findAll();
    }

    @GetMapping(path = "/Categories/{categoryId}/Posts/{postId}")
    PostForm getPostById(@PathVariable("categoryId") int categoryId, @PathVariable("postId") int postId)
    {
        return postsRepository.findById(postId);
    }

    @PostMapping(path = "/Categories/{categoryId}/Posts")
    String createPost(@RequestBody PostForm newPost, @PathVariable("categoryId") int categoryId)
    {
        if (newPost == null)
            return null;
        newPost.setCategoryForm(categoryRepository.findById(categoryId));
        postsRepository.save(newPost);
        return success;
    }

    @DeleteMapping(path = "/Categories/{categoryId}/Posts")
    String deleteAllPostsInCategory(@PathVariable("categoryId") int categoryId)
    {
        postsRepository.deleteAll();
        return success;
    }

    @DeleteMapping(path = "/Categories/{categoryId}/Posts/{postId}")
    String deletePostById(@PathVariable("categoryId") int categoryId, @PathVariable("postId") int postId)
    {
        PostForm post = postsRepository.findById(postId);
        postsRepository.deleteById(postId);
        return success;
    }
}
