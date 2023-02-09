package gamedr.Category;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gamedr.Post.PostForm;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
public class CategoryForm
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    private String categoryName;

    @OneToMany(mappedBy = "categoryForm", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PostForm> postForms = new LinkedHashSet<>();

    public Set<PostForm> getPostForms() {
        return postForms;
    }

    public void setPostForms(Set<PostForm> postForms) {
        this.postForms = postForms;
    }

    public CategoryForm(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public CategoryForm() {}


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
