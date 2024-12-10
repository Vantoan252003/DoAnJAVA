package ecourse.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Entity(name = "courses")
public class Course {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "course_id")
    private short courseId;
    @Column(name = "title")
    private String title;
    @Column(name = "created_at",nullable = true)
    private Date createdAt;
    @Column(name = "description")
    private String description;
    // @Column(name = "teacher_id")
    // private short teacherId;
    @Column(name = "image_url")
    private String image_url;
    @Transient
    private MultipartFile imageFile;
    // public short getTeacherId() {
    //     return teacherId;
    // }
    // public void setTeacherId(short teacherId) {
    //     this.teacherId = teacherId;
    // }
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
    //nối khóa học
    @OneToMany(mappedBy = "Course")
    private List<Enrollments> enrollM;
    public List<Enrollments> getEroll(){
        return enrollM;
    }
    public void setEnroll(List <Enrollments> enroll){
        this.enrollM = enroll;
    }
    //nối xong
    //nối đơn hàng
    @OneToMany(mappedBy = "course")
    private List<Order> order;
    public List<Order> getOrder(){
        return order;
    }
    public void setOrder(List <Order> order){
        this.order = order;
    }
    //nối tiếp
    @OneToMany(mappedBy = "course")
    private List<Lessons> lesson;
    public List<Lessons> getLessonM(){
        return lesson;
    }
    public void setLessonM(List <Lessons> lesson){
        this.lesson = lesson;
    }
    // nối xong
    //nối giảng viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    //nối xong
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
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Date.valueOf(LocalDate.now());
        }
    }
}
