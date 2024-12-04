package ecourse.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "lessons")
public class Lessons {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "lesson_id")
    private short lessonId;

    // @Column(name = "course_id")
    // private short courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "created_at")
    private Date createdAt;
    //nối khóa học
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    //nối xong
    public short getLessonId() {
        return lessonId;
    }

    public void setLessonId(short lessonId) {
        this.lessonId = lessonId;
    }

    // public short getCourseId() {
    //     return courseId;
    // }

    // public void setCourseId(short courseId) {
    //     this.courseId = courseId;
    // }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
