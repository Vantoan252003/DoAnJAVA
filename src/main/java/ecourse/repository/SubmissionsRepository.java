package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.Submissions;

public interface SubmissionsRepository extends CrudRepository<Submissions, Short> {
    

}
