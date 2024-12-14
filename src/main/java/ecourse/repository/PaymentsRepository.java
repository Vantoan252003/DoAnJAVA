package ecourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecourse.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Short> {
}
