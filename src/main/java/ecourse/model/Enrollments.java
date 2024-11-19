package ecourse.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "enrollments")
public class Enrollments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="enrollment_id")
    private short enrollId;
    @Column(name = "user_id")
    private short userId;
    @Column (name ="course_id")
    private short courseId;
    @Column(name ="enrolled_at")
    private Date enrolledDate;
    public short getEnrollId() {
        return enrollId;
    }
    public void setEnrollId(short enrollId) {
        this.enrollId = enrollId;
    }
    public short getUserId() {
        return userId;
    }
    public void setUserId(short userId) {
        this.userId = userId;
    }
    public short getCourseId() {
        return courseId;
    }
    public void setCourseId(short courseId) {
        this.courseId = courseId;
    }
    public Date getEnrolledDate() {
        return enrolledDate;
    }
    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }
    
}
