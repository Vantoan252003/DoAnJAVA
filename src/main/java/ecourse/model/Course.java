package ecourse.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Entity(name = "courses")
public class Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "course_id")
    private short courseId;
    @Column(name = "title")
    private String title;
    @Column(name = "created_at", nullable = true)
    private Date createdAt;
    @Column(name = "description")
    private String description;
    // @Column(name = "teacher_id")
    // private short teacherId;
    @Column(name = "image_url")
    private String image_url;
    @Transient
    private MultipartFile imageFile;

    public List<Enrollments> getEnrollM() {
        return enrollM;
    }

    public void setEnrollM(List<Enrollments> enrollM) {
        this.enrollM = enrollM;
    }

    @Column(name = "price")
    private float price;
    // nối khóa học
    @OneToMany(mappedBy = "course")
    private List<Enrollments> enrollM;

    public List<Enrollments> getEroll() {
        return enrollM;
    }

    public void setEnroll(List<Enrollments> enroll) {
        this.enrollM = enroll;
    }

    // nối xong
    // nối đơn hàng
    @OneToMany(mappedBy = "course")
    private List<Order> order;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    // nối tiếp
    @OneToMany(mappedBy = "course")
    private List<Lessons> lesson;

    public List<Lessons> getLessonM() {
        return lesson;
    }

    public void setLessonM(List<Lessons> lesson) {
        this.lesson = lesson;
    }

    // nối xong
    // nối giảng viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // nối xong
    // nối với categories
     @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "course_categories",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Categories> categories = new ArrayList<>();

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    // nối xong
    public short getCourseId() {
        return courseId;
    }

    public void setCourseId(short courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Date.valueOf(LocalDate.now());
        }
    }
}
