package ecourse.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;


@Entity(name = "categories")
public class Categories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    // nối khóa học
    @ManyToMany(mappedBy = "categories")
    private List<Course> courses; // Changed from single Course to List<Course>

    public List<Course> getCourses() { // Updated getter
        return courses;
    }

    public void setCourses(List<Course> courses) { // Updated setter
        this.courses = courses;
    }

    // nối xong
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
