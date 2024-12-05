package ecourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecourse.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Short> {
    
}
