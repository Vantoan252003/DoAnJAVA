package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Lessons;
import ecourse.model.LessonsRepository;


    @Controller
public class LessonController {
    @Autowired private LessonsRepository lessonsRepository;
    @GetMapping("/admin/lessons")
    public String index(Model model){
        model.addAttribute("list", lessonsRepository.findAll());
        return "admin/lessons/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/lessons/add")
    public String add(){
        return "admin/lessons/add";
    }
   
    @PostMapping("/admin/lessons/add")
    public String add(@ModelAttribute Lessons lessons){
        lessonsRepository.save(lessons);
        return "redirect:/admin/lessons";
    }
}
