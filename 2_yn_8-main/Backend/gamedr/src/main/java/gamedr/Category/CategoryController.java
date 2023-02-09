package gamedr.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController
{
    @Autowired
    CategoryRepository categoryRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/Categories")
    List<CategoryForm> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/Categories/{id}")
    CategoryForm getCategoryById(@PathVariable int id)
    {
        return categoryRepository.findById(id);
    }

    @PostMapping(path = "/Categories")
    String createCategory(@RequestBody CategoryForm newCategory)
    {
        if (newCategory == null)
            return failure;
        categoryRepository.save(newCategory);
        return success;
    }

    @PutMapping("/Categories/{id}")
    CategoryForm updateCategory(@PathVariable int id, @RequestParam String categoryName)
    {
        CategoryForm updatedForm = categoryRepository.findById(id);
        updatedForm.setCategoryName(categoryName);
        categoryRepository.save(updatedForm);
        return categoryRepository.findById(id);
    }

    @DeleteMapping(path = "Categories")
    String deleteAllCategories()
    {
        categoryRepository.deleteAll();
        return success;
    }

    @DeleteMapping(path = "/Categories/{id}")
    String deleteCategoryById(@PathVariable int id)
    {
        CategoryForm category = categoryRepository.findById(id);
        categoryRepository.deleteById(id);
        return success;
    }

}
