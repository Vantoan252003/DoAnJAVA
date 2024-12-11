package ecourse.controller;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ecourse.model.UserClass;
import ecourse.service.UserService;

@Controller
@RequestMapping("/home")
public class ProfileController {

    @Autowired
    private UserService userService; // Service để quản lý người dùng

    @PostMapping("/home/profile")
    public String updateProfilePicture(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            // Lấy người dùng đang đăng nhập
            String username = principal.getName();
            UserClass user = userService.findByUsername(username);

            // Xử lý file ảnh
            String fileName = file.getOriginalFilename();
            String uploadDir = "src/main/resources/static/img/";
            File uploadFile = new File(uploadDir + fileName);
            file.transferTo(uploadFile);

            // Cập nhật đường dẫn ảnh đại diện vào cơ sở dữ liệu
            user.setProfilePicture("/img/" + fileName);
            userService.save(user);

            return "redirect:/profile"; // Điều hướng đến trang profile
        // Lưu file vào hệ thống hoặc Cloud Storage (Ví dụ lưu vào server)
       
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/profile"; // Nếu có lỗi, quay lại trang profile
        }
    }
}
