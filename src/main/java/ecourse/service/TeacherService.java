package ecourse.service;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.model.Teacher;
import ecourse.repository.TeacherRepository;

import org.springframework.util.StringUtils;

@Service
public class TeacherService {
    @Autowired private TeacherRepository teacherRepository;

    @SuppressWarnings("null") 
    public void updateTeacher(Teacher teacher){
        if (teacher.getImageFile() != null && !( teacher.getImageFile()).isEmpty()) {
            String fileName = StringUtils.cleanPath(teacher.getImageFile().getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Lỗi không thể lưu file");
            }
            try {
                teacher.setImageUrl(Base64.getEncoder().encodeToString(teacher.getImageFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Teacher existingTeacher = teacherRepository.findById(teacher.getTeacherId()).orElse(null);
            if (existingTeacher != null) {
                teacher.setImageUrl(existingTeacher.getImageUrl());
            }
        }
        teacherRepository.save(teacher);
    }

    public void updatTeacher(Teacher teacher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatTeacher'");
    }
}
