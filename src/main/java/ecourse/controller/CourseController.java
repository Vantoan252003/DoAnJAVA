package ecourse.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Course;
import ecourse.repository.CourseRepository;
import ecourse.repository.LessonsRepository;
import ecourse.repository.TeacherRepository;
import ecourse.service.CourseService;
import ecourse.model.Lessons;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired 
    private CourseService courseService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonsRepository lessonsRepository;
    @GetMapping("/admin/course")
    public String index(Model model) {
        model.addAttribute("list", courseRepository.findAll());
        return "admin/course/index";
    }

    // Thêm dữ liệu
    @GetMapping("/admin/course/add")
    public String add(Model model) {
        model.addAttribute("teacherList", teacherRepository.findAll());
        model.addAttribute("courseList", courseRepository.findAll());
        return "admin/course/add";
    }

    @PostMapping("/admin/course/add")
    public String add(@ModelAttribute Course course) {
        courseService.updateCourse(course);
        return "redirect:/admin/course";
    }

    // Sửa dữ liệu
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

    // Xóa
    @GetMapping("/admin/course/delete/{courseId}")
    public String delete(@PathVariable("courseId") short courseId) {
        courseRepository.deleteById(courseId);
        return "redirect:/admin/course";
    }
    // User
    @GetMapping("/home/detailCourse/{courseId}")
    public String detailCourse(@PathVariable("courseId") short courseId, Model model) {
        Course course = courseRepository.findById(courseId).orElse(null);
        model.addAttribute("course", course);

        List<Lessons> lessons = lessonsRepository.findByCourse_CourseId(courseId); // Cần có method này
        model.addAttribute("lessons", lessons);
        Lessons firstLesson = lessons.isEmpty() ? null : lessons.get(0);
        model.addAttribute("firstLesson", firstLesson);
        model.addAttribute("teacher", teacherRepository.findAll());
        return "home/detailCourse";
    }
}




