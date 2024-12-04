package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecourse.model.UserClass;
import ecourse.repository.CourseRepository;
import ecourse.service.UserInterface;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class LayoutController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private UserInterface userService;

    @GetMapping("/home/course")
    public String course(Model model) {
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

    @GetMapping("/home/signin")
    public String login() {
        return "home/login";
    }
    @GetMapping("/home/register")
    public String register() {
        return "home/register";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserClass user, HttpSession session) {

        boolean f = userService.checkEmail(user.getEmail());
        if (f) {
            session.setAttribute("msg", "Email đã tồn tại");
        } else {
            UserClass userClass = userService.createUser(user);
            session.setAttribute("msg", userClass != null ? "Đăng ký thành công" : "Đăng ký thất bại");
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
    @GetMapping("/home/profile")
    public String profile() {
        return "home/profile";
    }
    
}
