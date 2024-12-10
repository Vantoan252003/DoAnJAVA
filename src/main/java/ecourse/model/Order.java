package ecourse.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private short orderId;
   
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
    //nối lại bảng user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserClass user;
    public UserClass getUser() {
        return user;
    }
    public void setUser(UserClass user) {
        this.user = user;
    }
    //nối xong
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING) // Ánh xạ enum thành chuỗi (ADMIN, SUPERADMIN)
    @Column(name = "status")
    private Status status;
    @Column(name = "total_price")
    private float totalPrice;
    public short getOrderId() {
        return orderId;
    }
    public void setOrderId(short orderId) {
        this.orderId = orderId;
    }
  

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
     @PrePersist
    protected void onCreate() {
        if (this.orderDate == null) {
            this.orderDate = LocalDateTime.now();
        }
    }
}
