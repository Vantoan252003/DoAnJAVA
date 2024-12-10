package ecourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ecourse.model.Order;
import ecourse.model.UserClass;
import ecourse.repository.OrderRepository;
import ecourse.repository.UserRepository;
import ecourse.secutiry.CustomUserDetails;

@Controller
public class OrderViewController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home/cart")
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Short currentPrincipalName = userDetails.getUserId();
        UserClass user = userRepository.findByUserId(currentPrincipalName);
        List<Order> orders = orderRepository.findOrdersByUser_UserId(user.getUserId());
        model.addAttribute("orders", orders);
        return "/home/cart";
    }
    @GetMapping("/home/deleteFromCart/{orderId}")
    public String deleteFromCart(@PathVariable("orderId") Short orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/home/cart";
    }
}
