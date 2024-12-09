package ecourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ecourse.model.Status;
import ecourse.model.Course;
import ecourse.model.Order;
import ecourse.model.UserClass;
import ecourse.repository.CourseRepository;
import ecourse.repository.OrderRepository;
import ecourse.repository.UserRepository;
import ecourse.secutiry.CustomUserDetails;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private CourseRepository courseRepository;
    public void addToCart(Short courseId) {
        // Lấy thông tin người dùng hiện tại
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            Short userId = userDetails.getUserId();

            // Kiểm tra xem khóa học có tồn tại hay không
            Course course = courseRepository.findById(courseId).orElseThrow(() -> 
                new IllegalArgumentException("Không tìm được khóa học: " + courseId)
            );

            // Kiểm tra xem người dùng đã thêm khóa học vào giỏ hàng chưa
            boolean alreadyInCart = orderRepository.existsByUserIdAndCourse_CourseId(userId, courseId);
            if (alreadyInCart) {
                throw new IllegalStateException("Khóa học này đã có trong giỏ hàng!");
            }
            // Thêm khóa học vào giỏ hàng
            Order order = new Order();
            order.setUserId(userId);
            order.setCourse(course);
            order.setStatus(Status.pending); // Giả định trạng thái là "PENDING"
            order.setTotalPrice(course.getPrice()); // Giả định totalPrice là giá của khóa học

            orderRepository.save(order);
        } else {
            throw new IllegalStateException("Chưa đăng nhập");
        }
    }
}
