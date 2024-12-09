package ecourse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ecourse.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update-avatar")
    public ResponseEntity<?> updateAvatar(@RequestParam("avatar") MultipartFile avatarFile) {
        // Lấy tên người dùng từ Spring Security (hoặc từ session)
        String username = getCurrentUsername();

        if (username == null) {
            return ResponseEntity.status(403).body("User not authenticated");
        }

        // Lưu file vào hệ thống hoặc Cloud Storage (Ví dụ lưu vào server)
        String imageUrl = userService.uploadAvatar(username, avatarFile);
        
        // Cập nhật URL ảnh trong cơ sở dữ liệu
        userService.updateUserImageUrl(username, imageUrl);

        // Trả về URL ảnh mới cho frontend
        return ResponseEntity.ok().body(new ResponseAvatar(imageUrl));
    }

    // Lấy tên người dùng hiện tại từ SecurityContext
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();  // Lấy username từ Spring Security
        }
        return null;
    }

    // DTO cho phản hồi (sử dụng URL ảnh mới)
    public static class ResponseAvatar {
        private String newImageUrl;

        public ResponseAvatar(String newImageUrl) {
            this.newImageUrl = newImageUrl;
        }

        public String getNewImageUrl() {
            return newImageUrl;
        }

        public void setNewImageUrl(String newImageUrl) {
            this.newImageUrl = newImageUrl;
        }
    }
}
