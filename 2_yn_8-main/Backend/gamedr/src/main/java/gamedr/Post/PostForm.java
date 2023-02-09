package gamedr.Post;

import gamedr.Users.User;
import gamedr.Category.CategoryForm;

import javax.persistence.*;

@Entity
@Table(name = "Posts")
public class PostForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String text;

    private String postName;

    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_form_id")
    private CategoryForm categoryForm;

    public CategoryForm getCategoryForm() {
        return categoryForm;
    }

    public void setCategoryForm(CategoryForm categoryForm) {
        this.categoryForm = categoryForm;
    }

    public PostForm() {}

    public PostForm(String postName, String text, String userName)
    {
        this.postName = postName;
        this.text = text;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
