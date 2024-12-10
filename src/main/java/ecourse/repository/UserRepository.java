package ecourse.repository;

import org.springframework.data.repository.CrudRepository;

import ecourse.model.UserClass;

public interface UserRepository extends CrudRepository<UserClass, Short> {
    public boolean existsByEmail(String email);

    public UserClass findByEmail(String email);

    public UserClass findByUsername(String username);
    public UserClass findByUserId(Short userId);
}
