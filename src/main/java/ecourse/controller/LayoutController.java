package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ecourse.model.CourseRepository;



@Controller
public class LayoutController {
    @Autowired CourseRepository courseRepository;
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
    
}
