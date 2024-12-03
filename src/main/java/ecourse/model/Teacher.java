package ecourse.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Entity(name = "teachers")
public class Teacher {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "teacher_id")
    private Short teacherId;
  // nối khóa học
    @ManyToOne
    @JoinColumn(name ="course_id")
    private Course Course;
    public Course getCourse() {
        return Course;
    }
    public void setCourse(Course Course) {
        this.Course = Course;
    }
    //nối xong
    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at", nullable = true)
    private Date createdAt;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "image_url")
    private String imageUrl;

    @Transient
    private MultipartFile imageFile;

    // Getters and Setters
    public Short getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Short teacherId) {
        this.teacherId = teacherId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Date.valueOf(LocalDate.now());

        }
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
    
}
