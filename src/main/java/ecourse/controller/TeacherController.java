package ecourse.controller;

import ecourse.model.Teacher;
import ecourse.model.TeacherRepository;
import ecourse.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model; 

@Controller
public class TeacherController {
    @Autowired private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/admin/teacher")
    public String index(Model model){
        model.addAttribute("list", teacherRepository.findAll());
        return "admin/teacher/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/teacher/add")
    public String add(){
        return "admin/teacher/add";
    }
   
    @PostMapping("/admin/teacher/add")
    public String add(@ModelAttribute Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "redirect:/admin/teacher";
    }
     //Sửa dữ liệu
    @GetMapping("/admin/teacher/edit/{teacherId}")
    public String edit(@PathVariable("teacherId") Short teacherId, Model model) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        model.addAttribute("teacher", teacher);
        return "admin/teacher/edit";
    }
    @PostMapping("/admin/teacher/edit/{teacherId}")
    public String update(@PathVariable("teacherId") short teacherId, @ModelAttribute Teacher post) {
        post.setTeacherId(teacherId);
        teacherService.updateTeacher(post);
        return "redirect:/admin/teacher";
    }
        //Xóa
    @GetMapping("/admin/teacher/delete/{teacherId}")
    public String delete(@PathVariable("teacherId") Short teacherId) {
        teacherRepository.deleteById(teacherId);
        return "redirect:/admin/teacher";
    }
    }

