package ecourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecourse.model.Order;

public interface OrderRepository extends JpaRepository<Order, Short> {
    boolean existsByUserIdAndCourse_CourseId(Short userId, Short courseId);
    public List<Order> findOrdersByUserId(Short userId);
}
