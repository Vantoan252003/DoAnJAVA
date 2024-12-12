package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;

import ecourse.model.UserClass;
import ecourse.service.UserService;

@Controller
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Để mã hóa mật khẩu

    // GET request để hiển thị trang đổi mật khẩu
    @GetMapping("/home/changePassword")
    public String showChangePasswordPage(Model model) {
        return "home/changePassword"; // Trả về trang changePassword.html
    }

    // POST request để thay đổi mật khẩu
    @PostMapping("/home/changePassword")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword) {
        try {
            // Lấy người dùng đang đăng nhập
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserClass user = userService.findByUsername(username);

            // Kiểm tra mật khẩu hiện tại có đúng không
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                // Kiểm tra nếu mật khẩu mới giống mật khẩu hiện tại
                if (newPassword.equals(currentPassword)) {
                    return "redirect:/home/changePassword?error=samePassword"; // Thông báo lỗi nếu mật khẩu mới giống mật khẩu cũ
                }

                // Kiểm tra mật khẩu mới và xác nhận mật khẩu mới có khớp không
                if (newPassword.equals(confirmPassword)) {
                    // Mã hóa mật khẩu mới và cập nhật vào cơ sở dữ liệu
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userService.save(user);
                    return "redirect:/home/profile"; // Quay lại trang profile sau khi đổi mật khẩu thành công
                } else {
                    // Nếu mật khẩu mới và xác nhận không khớp
                    return "redirect:/home/changePassword?error=passwordMismatch";
                }
            } else {
                // Nếu mật khẩu hiện tại không đúng
                return "redirect:/home/changePassword?error=invalidPassword";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home/changePassword?error=unknownError";
        }
    }
}
