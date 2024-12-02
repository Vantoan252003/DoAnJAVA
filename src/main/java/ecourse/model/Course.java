package ecourse.model;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity(name = "courses")
public class Course {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "course_id")
    private short courseId;
    @Column(name = "title")
    private String title;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "description")
    private String description;
    @Column(name = "teacher_id")
    private short teacherId;
    @Column(name = "image_url")
    private String image_url;
    public short getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(short teacherId) {
        this.teacherId = teacherId;
    }
    public List<Enrollments> getEnrollM() {
        return enrollM;
    }
    public void setEnrollM(List<Enrollments> enrollM) {
        this.enrollM = enrollM;
    }

    @Column(name ="price")
    private float price;
    @Column(name = "category")
    private String category;
    @Transient
    private MultipartFile imageFile;
    @OneToMany(mappedBy = "Course")
    private List<Enrollments> enrollM;
    public List<Enrollments> getEroll(){
        return enrollM;
    }
    public void setEnroll(List <Enrollments> enroll){
        this.enrollM = enroll;
    }
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
    
}
