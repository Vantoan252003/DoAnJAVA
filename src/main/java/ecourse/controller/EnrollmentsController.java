package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ecourse.model.EnrollmentsRepository;



@Controller
public class EnrollmentsController {
    
    @Autowired EnrollmentsRepository enrRepository;
    @GetMapping("/admin/enrollments")
    public String index(Model model) {
        model.addAttribute("list",enrRepository.findAll());
        return "admin/enrollments/index";
    }
    
}
