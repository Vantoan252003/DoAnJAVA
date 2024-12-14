// package ecourse.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;
// import ecourse.model.Payments;
// import ecourse.model.PaymentsMethod;
// import ecourse.model.PaymentsStatus;
// import ecourse.model.Order;
// import ecourse.repository.PaymentsRepository;
// import ecourse.repository.OrderRepository;
// import ecourse.secutiry.CustomUserDetails;

// import java.time.LocalDateTime;

// @Service
// public class PaymentsService {

//     @Autowired
//     private PaymentsRepository paymentsRepository;

//     @Autowired
//     private OrderRepository orderRepository;

//     public void processPayment(Short orderId, PaymentsMethod paymentMethod) {
//         // Lấy thông tin người dùng hiện tại
//         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         if (principal instanceof CustomUserDetails) {

//             // Kiểm tra xem order có tồn tại hay không
//             Order order = orderRepository.findById(orderId).orElseThrow(() ->
//                     new IllegalArgumentException("Không tìm được order: " + orderId)
//             );

//             // Tạo payment mới
//             Payments payment = new Payments();
//             payment.setOrder(order);
//             payment.setPaymentDate(LocalDateTime.now());
//             payment.setPaymentMethod(paymentMethod);
//             payment.setAmount(order.getTotalPrice()); // Lấy giá trị thanh toán từ tổng giá trị order

//             paymentsRepository.save(payment);
//         } else {
//             throw new IllegalStateException("Chưa đăng nhập");
//         }
//     }

//     public void updatePaymentStatus(Short paymentId, PaymentsStatus status) {
//         // Tìm payment theo ID
//         Payments payment = paymentsRepository.findById(paymentId).orElseThrow(() ->
//                 new IllegalArgumentException("Không tìm được payment: " + paymentId)
//         );

//         // Cập nhật trạng thái thanh toán
//         payment.setPaymentsStatus(status);
//         paymentsRepository.save(payment);
//     }
// }
