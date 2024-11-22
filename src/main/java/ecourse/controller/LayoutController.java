package ecourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LayoutController {
    @GetMapping("/home/course")
    public String course() {
        return "home/course";
    }
    @GetMapping("/home/contact")
    public String contact() {
        return "home/contact";
    }
    @GetMapping("/home/about")
    public String about() {
        return "home/about";
    }
    @GetMapping("/home/teacher")
    public String teacher() {
        return "home/teacher";
    }
    
}
