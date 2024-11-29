package ecourse.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "submissions")
public class Submissions {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "submission_id")
    private short submissionId;

    @Column(name = "assignment_id")
    private short assignmentId;

    @Column(name = "user_id")
    private short userId;
    // @ManyToOne
    // @JoinColumn(name ="assignmets_id")
    // private Assignments assign;
    // public Assignments getassign() {
    //     return assign;
    // }
 
    // public void setassign(Assignments assign) {
    //     this.assign = assign;
    // }
    // @ManyToOne
    // @JoinColumn(name ="user_id")
    // private UserClass UserClass;
    // public UserClass getUserClass() {
    //     return UserClass;
    // }
    // public void setUserId(UserClass UserClass) {
    //     this.UserClass = UserClass;
    // }
    @Column(name = "content")
    private String content;

    @Column(name = "submitted_at")
    private Date submittedAt;

    public short getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(short submissionId) {
        this.submissionId = submissionId;
    }

    public short getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(short assignmentId) {
        this.assignmentId = assignmentId;
    }

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

}
