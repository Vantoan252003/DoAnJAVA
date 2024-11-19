package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecourse.model.Enrollments;
import ecourse.model.EnrollmentsRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class EnrollmentsController {
    
    @Autowired EnrollmentsRepository enrRepository;
    @GetMapping("/admin/enrollments")
    public String index(Model model) {
        model.addAttribute("list",enrRepository.findAll());
        return "admin/enrollments/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/enrollments/add")
    public String add() {
        return "/admin/enrollments/add";
    }
    @PostMapping("/admin/enrollments/add")
    public String add(@ModelAttribute Enrollments post) {
        enrRepository.save(post);
        return "redirect:/admin/enrollments";
    }
    
    
}
