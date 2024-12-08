package ecourse.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ecourse.model.UserClass;
import ecourse.repository.UserRepository;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @SuppressWarnings("null")
    public void updateImage(UserClass userClass) {
        if (userClass.getImageFile() != null && !userClass.getImageFile().isEmpty()) {
            String fileName = StringUtils.cleanPath(userClass.getImageFile().getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Lỗi không thể lưu file");
            }
            try {
                userClass.setUserImageUrl(Base64.getEncoder().encodeToString(userClass.getImageFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            UserClass existingCourse = userRepository.findById(userClass.getUserId()).orElse(null);
            if (existingCourse != null) {
                userClass.setUserImageUrl(existingCourse.getUserImageUrl());
            }
        }
        userRepository.save(userClass);
    }
    // Tìm người dùng theo email
    public UserClass findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    // Lưu người dùng
    public void save(UserClass user) {
        userRepository.save(user);
    }
}
