package ecourse.controller;


import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecourse.model.Enrollments;
import ecourse.model.Order;
import ecourse.model.Status;

import ecourse.repository.OrderRepository;
import ecourse.service.EnrollmentsService;
import ecourse.service.OrderService;








@RestController
public class OrderController {
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private EnrollmentsService enrollmentsService;
    @GetMapping("/home/addToCart/{courseId}")
    public String addToCart(@PathVariable(name = "courseId") Short courseId, RedirectAttributes redirectAttributes) {
        try {
            orderService.addToCart(courseId);
            redirectAttributes.addFlashAttribute("message", "Khóa học đã thêm thành công!");
            return "Course has been added to your cart!";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return e.getMessage();
        }
    }
   @GetMapping("/admin/approve/{orderId}")
    public ResponseEntity<String> approveOrder(@PathVariable("orderId") Short orderId) {
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            
            if (order != null) {
                // Create new enrollment
                Enrollments enrollment = new Enrollments();
                enrollment.setClazz(order.getUser());
                enrollment.setCourse(order.getCourse());
                enrollment.setEnrolledDate(new Date(System.currentTimeMillis())); 
                
                // Save enrollment
                enrollmentsService.save(enrollment);
                
                // Update order status
                order.setStatus(Status.completed);
                orderRepository.save(order);
                orderRepository.deleteById(orderId);
                
                return ResponseEntity.ok("Ghi danh khóa học thành công!");
            }
            return ResponseEntity.badRequest().body("Không tìm thấy đơn hàng!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
    
    
    
}
    

