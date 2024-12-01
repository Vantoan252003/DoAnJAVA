package ecourse.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserClass, Short> {
    public boolean existsByEmail(String email);

}
