package ecourse.service;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.model.Course;
import ecourse.model.CourseRepository;
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
        }
        courseRepository.save(course);
    }
}
