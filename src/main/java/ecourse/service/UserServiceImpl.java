package ecourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecourse.model.UserClass;
import ecourse.repository.UserRepository;
@Service
public class UserServiceImpl implements UserInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public UserClass createUser(UserClass user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserImageUrl("/9j/4AAQSkZJRgABAQEBLAEsAAD/4QBWRXhpZgAATU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAAITAAMAAAABAAEAAAAAAAAAAAEsAAAAAQAAASwAAAAB/+0ALFBob3Rvc2hvcCAzLjAAOEJJTQQEAAAAAAAPHAFaAAMbJUccAQAAAgAEAP/hDIFodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0nYWRvYmU6bnM6bWV0YS8nIHg6eG1wdGs9J0ltYWdlOjpFeGlmVG9vbCAxMC4xMCc+CjxyZGY6UkRGIHhtbG5zOnJkZj0naHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyc+CgogPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9JycKICB4bWxuczp0aWZmPSdodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyc+CiAgPHRpZmY6UmVzb2x1dGlvblVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICA8dGlmZjpYUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpYUmVzb2x1dGlvbj4KICA8dGlmZjpZUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpZUmVzb2x1dGlvbj4KIDwvcmRmOkRlc2NyaXB0aW9uPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6eG1wTU09J2h0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8nPgogIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnN0b2NrOmZhNTdiNjViLWE1MmUtNGUzOS04YjgyLWQ5NjhjMjc3NjgzYTwveG1wTU06RG9jdW1lbnRJRD4KICA8eG1wTU06SW5zdGFuY2VJRD54bXAuaWlkOjBjZDMyNTZjLTQ4MmItNDMxMS05NTViLTQzN2I2ZDU2NmJlNzwveG1wTU06SW5zdGFuY2VJRD4KIDwvcmRmOkRlc2NyaXB0aW9uPgo8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSd3Jz8+/9sAQwAFAwQEBAMFBAQEBQUFBgcMCAcHBwcPCwsJDBEPEhIRDxERExYcFxMUGhURERghGBodHR8fHxMXIiQiHiQcHh8e/8AACwgBaAFoAQERAP/EABwAAQADAAMBAQAAAAAAAAAAAAAFBgcCAwQBCP/EAEQQAQABAwIBBgsEBwcFAQAAAAABAgMEBREGEiExQVGxBxMyNmFxdIGRodEiI0LBFBVicpKi4SQ1Q1KC0vAWM1NVY5P/2gAIAQEAAD8A/ZYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA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");
        return userRepository.save(user);
    }
    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
