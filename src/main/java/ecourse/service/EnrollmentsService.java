package ecourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.model.Enrollments;
import ecourse.repository.EnrollmentsRepository;

@Service
public class EnrollmentsService {
    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    
    public Enrollments save(Enrollments enrollment) {
        return enrollmentsRepository.save(enrollment);
    }
}
