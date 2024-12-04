package ecourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import ecourse.model.Lessons;

public interface LessonsRepository extends JpaRepository<Lessons, Short> {
    List<Lessons> findByCourse_CourseId(Short courseId);
}
