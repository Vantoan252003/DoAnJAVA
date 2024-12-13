package ecourse.service;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ecourse.model.Course;
import ecourse.repository.CourseRepository;

import org.springframework.util.StringUtils;

@Service
public class CourseService {
    @Autowired private CourseRepository courseRepository;

    @SuppressWarnings("null")
    public void updateCourse(Course course) {
        if (course.getImageFile() != null && !course.getImageFile().isEmpty()) {
            String fileName = StringUtils.cleanPath(course.getImageFile().getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Lỗi không thể lưu file");
            }
            try {
                course.setImage_url(Base64.getEncoder().encodeToString(course.getImageFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Course existingCourse = courseRepository.findById(course.getCourseId()).orElse(null);
            if (existingCourse != null) {
                course.setImage_url(existingCourse.getImage_url());
            }
        }
        courseRepository.save(course);
    }
    public List<Course> getAllCoursesWithCategories() {
        return courseRepository.findAllWithCategories(); 
    }

}
