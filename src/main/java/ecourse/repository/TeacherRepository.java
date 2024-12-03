package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Short> {

}
