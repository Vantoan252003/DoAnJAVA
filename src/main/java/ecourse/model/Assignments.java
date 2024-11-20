package ecourse.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "assignments")
public class Assignments {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "assignment_id")
    private short assignmentId;

    @Column(name = "lesson_id")
    private short lessonId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "created_at")
    private Date createdAt;

    public short getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(short assignmentId) {
        this.assignmentId = assignmentId;
    }

    public short getLessonId() {
        return lessonId;
    }

    public void setLessonId(short lessonId) {
        this.lessonId = lessonId;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
