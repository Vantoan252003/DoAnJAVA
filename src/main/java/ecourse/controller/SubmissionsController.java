package ecourse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.CourseRepository;
import ecourse.model.Submissions;
import ecourse.model.SubmissionsRepository;
import ecourse.model.UserRepository;

@Controller
public class SubmissionsController {
    @Autowired private SubmissionsRepository submissionsRepository;
    @Autowired UserRepository assign;
    @Autowired CourseRepository user;
    @GetMapping("/admin/submissions")
    public String index(Model model){
        model.addAttribute("list", submissionsRepository.findAll());
   
        return "admin/submissions/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/submissions/add")
    public String add(Model model){
        model.addAttribute("assignList", assign.findAll());
        model.addAttribute("userList", user.findAll());
        return "admin/submissions/add";
    }
   
    @PostMapping("/admin/submissions/add")
    public String add(@ModelAttribute Submissions submissions){
        submissionsRepository.save(submissions);
        return "redirect:/admin/submissions";
    }
    //Sửa dữ liệu
    @GetMapping("/admin/submissions/edit/{submissionId}")
    public String edit(@PathVariable("submissionId") short submissionId, Model model) {
        Submissions sub = submissionsRepository.findById(submissionId).orElse(null);
        model.addAttribute("sub", sub);
        return "admin/submissions/edit";
    }
    @PostMapping("/admin/submissions/edit/{submissionId}")
    public String update(@PathVariable("submissionId") short submissionId, @ModelAttribute Submissions post) {
        post.setSubmissionId(submissionId);
        submissionsRepository.save(post);
        return "redirect:/admin/submissions";
    }
    //Xóa
    @GetMapping("/admin/submissions/delete/{submissionId}")
    public String delete(@PathVariable("submissionId") short submissionId) {
        submissionsRepository.deleteById(submissionId);
        return "redirect:/admin/submissions";
    }
}
