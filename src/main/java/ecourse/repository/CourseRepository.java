package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Course;

public interface CourseRepository extends CrudRepository<Course, Short> {

}
