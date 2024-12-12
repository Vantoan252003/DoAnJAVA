package ecourse.controller;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // GET request để trả về trang avatar
    @GetMapping("/avatar")
    public String getAvatarPage(Model model, Principal principal) {
        try {
            // Lấy người dùng đang đăng nhập
            String username = principal.getName();
            UserClass user = userService.findByUsername(username);

            // Thêm dữ liệu người dùng vào model
            model.addAttribute("user", user);

            return "avatar"; // Trả về trang avatar.html
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/profile"; // Nếu có lỗi, quay lại trang profile
        }
    }

    // POST request để cập nhật ảnh đại diện
    @PostMapping("/avatar")
    public String updateProfilePicture(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            // Lấy người dùng đang đăng nhập
            String username = principal.getName();
            UserClass user = userService.findByUsername(username);

            // Xử lý file ảnh
            String fileName = file.getOriginalFilename();
            String uploadDir = "src/main/resources/static/img";
            File uploadFile = new File(uploadDir + fileName);
            file.transferTo(uploadFile);

            // Cập nhật đường dẫn ảnh đại diện vào cơ sở dữ liệu
            user.setProfilePicture("/img/" + fileName);
            userService.save(user);

            return "redirect:/home/avatar"; // Điều hướng đến trang avatar sau khi cập nhật ảnh
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home/avatar"; // Nếu có lỗi, quay lại trang avatar
        }
    }
}
