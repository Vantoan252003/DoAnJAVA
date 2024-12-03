package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Assignments;

public interface AssignmentsRepository extends CrudRepository<Assignments, Short> {
    

}
