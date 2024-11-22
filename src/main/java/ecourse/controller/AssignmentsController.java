package ecourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Assignments;
import ecourse.model.AssignmentsRepository;
import ecourse.model.Enrollments;

import org.springframework.web.bind.annotation.RequestParam;



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
        return "redirect:/admin/assignments";
    }
    //Sửa dữ liệu
    @GetMapping("/admin/assignmets/edit/{assignmentId}")
    public String edit(@PathVariable("assignmentId") short assignmentId, Model model) {
    Assignments assignments = assignmentsRepository.findById(assignmentId).orElse(null);
    model.addAttribute("assignmets", assignments);
    return "admin/assignments/edit";
    }
    @PostMapping("/admin/assignments/edit/{assignmentId}")
    public String update(@PathVariable("assignments") short assignmentId, @ModelAttribute Assignments assignments) {
        assignments.setAssignmentId(assignmentId);
        assignmentsRepository.save(assignments);
        return "redirect:/admin/assignments";
    }
    //Xóa
    @GetMapping("/admin/assignments/delete/{assignmentId}")
    public String delete(@PathVariable("assignmentId") short assignmentId) {
        assignmentsRepository.deleteById(assignmentId);
        return "redirect:/admin/assignments";
    }
    
}
