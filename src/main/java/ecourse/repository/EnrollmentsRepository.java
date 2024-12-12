package ecourse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Enrollments;

public interface EnrollmentsRepository  extends CrudRepository<Enrollments, Short>{
    public List <Enrollments> findEnrollmentsByClazz_UserId(Short userId);
}
