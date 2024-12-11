package ecourse.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import ecourse.model.Order;
import ecourse.model.UserClass;
import ecourse.repository.ContactRepository;
import ecourse.repository.OrderRepository;
import ecourse.repository.UserRepository;




@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired OrderRepository orderRepository;
    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        UserClass user = userRepository.findByEmail(email);
        m.addAttribute("user", user);
    }
    @GetMapping
    public String getAdminPage(Model model) {
        // Lấy danh sách các đơn hàng
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("contacts", contactRepository.findAll(Sort.by(Sort.Direction.DESC, "submittedAt")));
        model.addAttribute("orders", orders); // Truyền dữ liệu vào model

        return "admin/index"; // Tên file HTML Thymeleaf
    }
  
    
}