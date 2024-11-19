package ecourse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Course;
import ecourse.model.CourseRepository;
import ecourse.service.CourseService;

@Controller
public class CourseController {
    @Autowired private CourseRepository courseRepository;
    @Autowired private CourseService courseService;
    @GetMapping("/admin/course")
    public String index(Model model){
        model.addAttribute("list", courseRepository.findAll());
        return "admin/course/index";
    }
    //Thêm dữ liệu
    @GetMapping("/admin/course/add")
    public String add(){
        return "admin/course/add";
    }
   
    @PostMapping("/admin/course/add")
    public String add(@ModelAttribute Course course){
        courseService.updateCourse(course);
        return "redirect:/admin/course";
    }
    //Sửa dữ liệu
    @GetMapping("/admin/course/edit/{courseId}")
    public String edit(@PathVariable("courseId") short courseId, Model model) {
        Course course = courseRepository.findById(courseId).orElse(null);
        model.addAttribute("course", course);
        return "admin/course/edit";
    }
    @PostMapping("/admin/course/edit/{courseId}")
    public String update(@PathVariable("courseId") short courseId, @ModelAttribute Course course) {
        course.setCourseId(courseId);
        courseService.updateCourse(course);
        return "redirect:/admin/course";
    }

}
