package ecourse.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecourse.model.Course;
import ecourse.repository.CategoriesRepository;
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
    @Autowired
    private CategoriesRepository categoryRepository;
    @GetMapping("/admin/course")
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCoursesWithCategories();
        model.addAttribute("list", courses);
        return "admin/course/index";
    }

    // Thêm dữ liệu
    @GetMapping("/admin/course/add")
    public String add(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("courseList", courseRepository.findAll());
        return "admin/course/add";
    }
    @GetMapping("/home/course/search")
public String searchCourses(@RequestParam(required = false) String keyword,
                          @RequestParam(required = false) Integer category,
                          Model model) {
    List<Course> searchResults;
    if (keyword != null && !keyword.isEmpty()) {
        if (category != null) {
            searchResults = courseRepository.findByTitleContainingAndCategoriesCategoryId(keyword, category);
        } else {
            searchResults = courseRepository.findByTitleContaining(keyword);
        }
    } else if (category != null) {
        searchResults = courseRepository.findByCategoriesCategoryId(category);
    } else {
        searchResults = courseRepository.findAll();
    }
    
    model.addAttribute("list", searchResults);
    model.addAttribute("categories", categoryRepository.findAll());
    model.addAttribute("keyword", keyword);
    model.addAttribute("selectedCategory", category);
    return "home/course";
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
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll()); // Thêm dòng này
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
    
        // Lấy danh sách lessons và xử lý URL video
        List<Lessons> lessons = lessonsRepository.findByCourse_CourseId(courseId);
        lessons.forEach(lesson -> {
            String videoUrl = lesson.getVideoUrl();
            if (videoUrl != null && videoUrl.contains("watch?v=")) {
                lesson.setVideoUrl(videoUrl.replace("watch?v=", "embed/")); // Thay thế "watch?v=" bằng "embed/"
            }
        });
        model.addAttribute("lessons", lessons);
        Lessons firstLesson = lessons.isEmpty() ? null : lessons.get(0);
        model.addAttribute("firstLesson", firstLesson);
    
        model.addAttribute("teacher", teacherRepository.findAll());
        return "home/detailCourse";
    }
}




