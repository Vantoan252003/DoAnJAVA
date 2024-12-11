package ecourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecourse.model.Submissions;
import ecourse.repository.SubmissionsRepository;

@Service
public class SubmissionService {
    @Autowired private SubmissionsRepository submissionsRepository;
    public Submissions save(Submissions submission) {
        return submissionsRepository.save(submission);
    }
}
