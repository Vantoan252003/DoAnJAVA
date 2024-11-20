package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import ecourse.model.CourseRepository;
import ecourse.model.Enrollments;
import ecourse.model.EnrollmentsRepository;
import ecourse.model.UserClass;
import ecourse.model.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class EnrollmentsController {
    
    @Autowired EnrollmentsRepository enrRepository;
    @Autowired UserRepository userRes;
    @Autowired CourseRepository course;
    @GetMapping("/admin/enrollments")
    public String index(Model model) {
        model.addAttribute("list",enrRepository.findAll());
        return "admin/enrollments/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/enrollments/add")
    public String add(Model model) {
        model.addAttribute("userList", userRes.findAll());
        model.addAttribute("courseList", course.findAll());
        return "/admin/enrollments/add";
    }
    @PostMapping("/admin/enrollments/add")
    public String add(@ModelAttribute Enrollments post) {
        enrRepository.save(post);
        return "redirect:/admin/enrollments";
    }
    //Sửa dữ liệu
    @GetMapping("/admin/enrollments/edit/{enrollId}")
    public String edit(@PathVariable("enrollId") short enrollId, Model model) {
    Enrollments enroll = enrRepository.findById(enrollId).orElse(null);
    model.addAttribute("enrollments", enroll);
    return "admin/enrollments/edit";
    }
    @PostMapping("/admin/enrollments/edit/{enrollId}")
    public String update(@PathVariable("enrollId") short enrollId, @ModelAttribute Enrollments post) {
        post.setEnrollId(enrollId);
        enrRepository.save(post);
        return "redirect:/admin/enrollments";
    }
    //Xóa
    @GetMapping("/admin/enrollments/delete/{enrollId}")
    public String delete(@PathVariable("enrollId") short enrollId) {
        enrRepository.deleteById(enrollId);
        return "redirect:/admin/enrollments";
    }
    
}
