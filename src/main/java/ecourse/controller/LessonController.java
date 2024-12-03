package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Lessons;
import ecourse.repository.LessonsRepository;


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
     //Sửa dữ liệu
    @GetMapping("/admin/lessons/edit/{lessonId}")
    public String edit(@PathVariable("lessonId") short lessonId, Model model) {
        Lessons lesson = lessonsRepository.findById(lessonId).orElse(null);
        model.addAttribute("lesson", lesson);
        return "admin/lessons/edit";
    }
    @PostMapping("/admin/lessons/edit/{lessonId}")
    public String update(@PathVariable("lessonId") short lessonId, @ModelAttribute Lessons post) {
        post.setLessonId(lessonId);
        lessonsRepository.save(post);
        return "redirect:/admin/lessons";
    }
    //Xóa
    @GetMapping("/admin/lessons/delete/{lessonId}")
    public String delete(@PathVariable("lessonId") short lessonId) {
        lessonsRepository.deleteById(lessonId);
        return "redirect:/admin/lessons";
    }
}
