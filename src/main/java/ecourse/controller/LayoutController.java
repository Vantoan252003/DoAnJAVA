package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecourse.model.CourseRepository;
import ecourse.model.UserClass;
import ecourse.service.UserInterface;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class LayoutController {
    @Autowired CourseRepository courseRepository;
    @Autowired private UserInterface userService;
    @GetMapping("/home/course")
    public String course(Model model){
        model.addAttribute("list", courseRepository.findAll());
        return "home/course";
    }
    @GetMapping("/home/index")
    public String index() {
        return "home/index";
    }
    @GetMapping("/home/contact")
    public String contact() {
        return "home/contact";
    }
    @GetMapping("/home/about")
    public String about() {
        return "home/about";
    }
    @GetMapping("/home/blog")
    public String blog() {
        return "home/blog";
    }
    @GetMapping("/home/single")
    public String single() {
        return "home/single";
    }
    @GetMapping("/home/teacher")
    public String teacher() {
        return "home/teacher";
    }
    @GetMapping("/home/login")
    public String login() {
        return "home/login";
    }
    @GetMapping("/home/register")
    public String register() {
        return "home/register";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserClass user, HttpSession session) { 
        // System.out.println(user);
        //TODO: process POST request
        boolean f= userService.checkEmail(user.getEmail());
        if (f) {
            session.setAttribute("msg", "Email đã tồn tại");
            return "redirect:/home/register";
            
        }
        else{
            UserClass userClass = userService.createUser(user);
            if(userClass != null) {
                session.setAttribute("msg", "Đăng ký thành công");
             }
            else{
                session.setAttribute("msg", "Đăng ký thất bại");
        }
        }
        
        return "redirect:/home/register";
    }
    @PostMapping("/clearSessionMsg")
public void clearSessionMsg(HttpSession session) {
    session.removeAttribute("msg");
}   
    
    @GetMapping("/home/forgot-password")
    public String forgotPassword() {
        return "home/forgot-password";
    }
}
