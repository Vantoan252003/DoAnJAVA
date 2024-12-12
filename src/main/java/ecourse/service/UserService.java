package ecourse.service;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ecourse.model.UserClass;
import ecourse.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
    
    public void updateFullname(String email, String fullname) {
        Optional<UserClass> userOpt = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOpt.isPresent()) {
            UserClass user = userOpt.get();
            user.setFullname(fullname); // Cập nhật fullname
            userRepository.save(user); // Lưu thông tin người dùng vào cơ sở dữ liệu
        }
    }
    
    public void updateFullname(String fullname) {
        throw new UnsupportedOperationException("Unimplemented method 'updateFullname'");
    }

    public String getUserImageUrl(String username) {
    throw new UnsupportedOperationException("Unimplemented method 'getUserImageUrl'");
    }

    public String uploadAvatar(String currentUser, MultipartFile avatarFile) {
    throw new UnsupportedOperationException("Unimplemented method 'uploadAvatar'");
    }

    public void updateUserImageUrl(String currentUser, String imageUrl) {
    throw new UnsupportedOperationException("Unimplemented method 'updateUserImageUrl'");
    }

}
