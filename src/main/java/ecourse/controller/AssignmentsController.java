package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Assignments;
import ecourse.model.AssignmentsRepository;


    @Controller
public class AssignmentsController {
    @Autowired private AssignmentsRepository assignmentsRepository;
    @GetMapping("/admin/assignments")
    public String index(Model model){
        model.addAttribute("list", assignmentsRepository.findAll());
        return "admin/assignments/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/assignments/add")
    public String add(){
        return "admin/assignments/add";
    }
   
    @PostMapping("/admin/assignments/add")
    public String add(@ModelAttribute Assignments assignments){
        assignmentsRepository.save(assignments);
        return "redirect:/admin/course";
    }
}
