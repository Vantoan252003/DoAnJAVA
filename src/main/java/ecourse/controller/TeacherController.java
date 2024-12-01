package ecourse.controller;
import ecourse.model.Teacher;
import ecourse.model.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

    @RestController
    @RequestMapping("/teachers")
    public class TeacherController {

        @Autowired
        private TeacherRepository teacherRepository;

        @GetMapping
        public Iterable<Teacher> getAllTeachers() {
            return teacherRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
            Optional<Teacher> teacher = teacherRepository.findById(id);
            if (teacher.isPresent()) {
                return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public Teacher createTeacher(@RequestBody Teacher teacher) {
            return teacherRepository.save(teacher);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacherDetails) {
            Optional<Teacher> teacher = teacherRepository.findById(id);
            if (teacher.isPresent()) {
                Teacher updatedTeacher = teacher.get();
                updatedTeacher.setFullname(teacherDetails.getFullname());
                updatedTeacher.setEmail(teacherDetails.getEmail());
                updatedTeacher.setPassword(teacherDetails.getPassword());
                updatedTeacher.setCreatedAt(teacherDetails.getCreatedAt());
                updatedTeacher.setSpecialty(teacherDetails.getSpecialty());
                updatedTeacher.setImageUrl(teacherDetails.getImageUrl());
                teacherRepository.save(updatedTeacher);
                return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable int id) {
            try {
                teacherRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

