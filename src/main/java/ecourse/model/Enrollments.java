package ecourse.model;


import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "enrollments")
public class Enrollments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="enrollment_id")
    private short enrollId;

    // @Column(name = "user_id")
    // private short userId;


    //nối người dùng
    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserClass clazz;
    public UserClass getClazz() {
        return clazz;
    }
    public void setClazz(UserClass clazz) {
        this.clazz = clazz;
    }
    //nối xong
    

    // nối khóa học
    @ManyToOne
    @JoinColumn(name ="course_id")
    private Course course;
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    //nối xong

    @Column(name ="enrolled_at")
    private Date enrolledDate;
    
    public short getEnrollId() {
        return enrollId;
    }
    public void setEnrollId(short enrollId) {
        this.enrollId = enrollId;
    }
    // public short getUserId() {
    //     return userId;
    // }
    // public void setUserId(short userId) {
    //     this.userId = userId;
    // } a little comment here
    // public short getCourseId() {
    //     return courseId;
    // }
    // public void setCourseId(short courseId) {
    //     this.courseId = courseId;
    // }
    public Date getEnrolledDate() {
        return enrolledDate;
    }
    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }
    
}
