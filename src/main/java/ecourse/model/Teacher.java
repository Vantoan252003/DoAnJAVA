package ecourse.model;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
    @Entity(name = "teachers")
    public class Teacher {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "teacher_id")
        private int teacherId;

        @Column(name = "fullname")
        private String fullname;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "created_at")
        private Timestamp createdAt;

        @Column(name = "specialty")
        private String specialty;

        @Column(name = "image_url")
        private String imageUrl;

        // Getters and Setters
        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
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

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
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
    }

