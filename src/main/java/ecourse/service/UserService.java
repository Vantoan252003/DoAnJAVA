package ecourse.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // public void updateFullname(String username, String fullname) {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if (authentication != null && authentication.isAuthenticated()) {
    //         String email = authentication.getName();

    //         // Tìm người dùng theo email
    //         UserClass user = userRepository.findByEmail(email);
    //         if (user != null) {
    //             // Cập nhật fullname
    //             user.setFullname(fullname);
    //             userRepository.save(user); // Lưu lại thông tin người dùng đã cập nhật
    //         } else {
    //             throw new UsernameNotFoundException("User not found");
    //         }
    //         if (user != null) {
    //             user.setFullname(fullname);
    //             userRepository.save(user);
    //             System.out.println("Updated fullname for user: " + username); // Log để kiểm tra
    //         }
    //     }
    // }
    public void updateFullname(String email, String fullname) {
        Optional<UserClass> userOpt = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOpt.isPresent()) {
            UserClass user = userOpt.get();
            user.setFullname(fullname); // Cập nhật fullname
            userRepository.save(user);  // Lưu thông tin người dùng vào cơ sở dữ liệu
        }
    }

    // public String getUserImageUrl(String username) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserImageUrl'");
    // }

    // public String uploadAvatar(String currentUser, MultipartFile avatarFile) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'uploadAvatar'");
    // }

    // public void updateUserImageUrl(String currentUser, String imageUrl) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateUserImageUrl'");
    // }


    // Phương thức lưu ảnh vào hệ thống
public String uploadAvatar(String username, MultipartFile avatarFile) {
    try {
        // Đường dẫn lưu ảnh vào thư mục admin/user
        String directory = "C:/Users/Nguye/DoAnJAVA/src/main/resources/static/images/";
        Path path = Paths.get(directory + username + "_" + avatarFile.getOriginalFilename());

        // Kiểm tra và tạo thư mục nếu không tồn tại
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs(); // Tạo thư mục nếu chưa có
        }
        // Lưu ảnh vào server
        avatarFile.transferTo(path); 

        // Trả về URL của ảnh sau khi upload
        return "/static/images/" + username + "_" + avatarFile.getOriginalFilename();
    } catch (IOException e) {
        throw new RuntimeException("Error uploading avatar", e);
    }
}


    // Phương thức cập nhật URL ảnh vào cơ sở dữ liệu
    @SuppressWarnings("unused")
    public void updateUserImageUrl(String username, String imageUrl) {
        // Cập nhật URL ảnh vào cơ sở dữ liệu của người dùng
        UserClass userImageUrl = userRepository.findByUsername(username); // Giả sử bạn có repository để tìm người dùng
        userImageUrl.setImageUrl(imageUrl);
        userRepository.save(userImageUrl);
        if (userImageUrl != null) {
            userImageUrl.setImageUrl(userImageUrl);
        } else {
            // Xử lý trường hợp userImageUrl là null (có thể gán giá trị mặc định)
            userImageUrl.setImageUrl("default-image-url");
        }
        
    }

    // Phương thức lấy URL ảnh đại diện người dùng
    public String getUserImageUrl(String username) {
        UserClass userImageUrl = userRepository.findByUsername(username);
        return userImageUrl != null ? userImageUrl.getImageUrl() : null;
    }
}




