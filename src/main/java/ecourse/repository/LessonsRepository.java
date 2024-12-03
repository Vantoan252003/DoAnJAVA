package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Lessons;

public interface LessonsRepository extends CrudRepository<Lessons, Short> {
    

}
