package ecourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecourse.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Short> {
    @Query("SELECT DISTINCT c FROM courses c LEFT JOIN FETCH c.categories")
    List<Course> findAllWithCategories();
}
