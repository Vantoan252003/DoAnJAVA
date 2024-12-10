package ecourse.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecourse.service.OrderService;






@RestController
public class OrderController {
    @Autowired private OrderService orderService;
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
    
    
}
    

